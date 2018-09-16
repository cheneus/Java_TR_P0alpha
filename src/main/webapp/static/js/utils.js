var userLog = function() {
  axios
    .post(
      'http://localhost:8080/Project1/login',
      {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
      },
      {
        params: {
          user: 'admin',
          pass: 'password'
        }
      }
    )
    .then(function(res) {
      console.log(res.data);
    })
    .catch(e => {
      console.log(e);
    });
};
window.onload = userLog();
