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
        $('#login_feedback').text("Wrong Username/Password");
        $('#login_feedback').css('display', 'block');
        console.log(e);
      });
  },
  getTR: function() {
    
  }
}
