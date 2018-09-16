var userLog = function(user, pass) {
  axios
    .post(
      'http://localhost:8080/Project1/login',
      {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
      },
      {
        params: {
          user: user,
          pass: pass
        }
      }
    )
    .then(function(res) {
      console.log(res.data);
    })
    .catch(function(e) {
      console.log(e);
    });
};

var utils = {
  userLogin: function() {
    var user = $('#username').val();
    var pass =$('#password').val();
    console.log(`${user}, ${pass}`)
    axios
      .post(
        'http://localhost:8080/Project1/login',
        {
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        },
        {
          params: {
            user: user,
            pass: pass
          }
        }
      )
      .then(function(res) {
        console.log(res.data);
      })
      .catch(function(e) {
        console.log(e);
      });
  },
}
