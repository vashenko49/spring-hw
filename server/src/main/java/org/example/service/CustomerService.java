package org.example.service;

import org.example.entity.Customer;
import org.example.exception.OAuth2AuthenticationProcessingException;
import org.example.repos.CustomerRepository;
import org.example.security.UserPrincipal;
import org.example.security.oauth2.user.OAuth2UserInfo;
import org.example.security.oauth2.user.OAuth2UserInfoFactory;
import org.example.service.imp.CustomerServiceIml;
import org.example.service.imp.ServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.Optional;

@Service("customerService")
@Transactional(isolation = Isolation.SERIALIZABLE)
public class CustomerService extends DefaultOAuth2UserService implements ServiceIml<Customer>, CustomerServiceIml {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer obj) {
        return customerRepository.save(obj);
    }

    @Override
    public Customer update(Customer obj) {
        return customerRepository.save(obj);
    }

    @Override
    public void delete(Customer obj) {
        customerRepository.delete(obj);
    }

    @Override
    public void deleteAll(List<Customer> ent) {
        customerRepository.deleteAll(ent);
    }

    @Override
    public void saveAll(List<Customer> ent) {
        customerRepository.saveAll(ent);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Customer> findAll(int page, int limit) {
        Page<Customer> all = customerRepository.findAll(PageRequest.of(page, limit));
        return all;
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getById(Long id) {
        Customer customer = customerRepository.findById(id).get();
        return customer;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Customer> user = customerRepository.findByEmail(s);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found userName"));
        return user.get();
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        return processOauth2User(userRequest, oAuth2User);
    }

    private OAuth2User processOauth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        String email = oAuth2UserInfo.getEmail();

        Optional<Customer> customerOptional = customerRepository.findByEmail(email);

        Customer customer;
        if (customerOptional.isPresent()) {
            customer = customerOptional.get();
            customer = updateExistingUser(customer, oAuth2UserInfo);
        } else {
            customer = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(customer, oAuth2User.getAttributes());
    }

    private Customer registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        Customer user = new Customer();
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        return customerRepository.save(user);
    }

    private Customer updateExistingUser(Customer existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        return customerRepository.save(existingUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
}
