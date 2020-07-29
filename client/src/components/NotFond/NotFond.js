import React from 'react';
import Container from '@material-ui/core/Container';
import Typography from '@material-ui/core/Typography';

const NotFond = () => {
  return (
    <Container>
      <Typography variant={'h4'} align={'center'}>
        Not Found
      </Typography>
      <Typography variant={'h1'} align={'center'}>
        404
      </Typography>
    </Container>
  );
};

export default NotFond;
