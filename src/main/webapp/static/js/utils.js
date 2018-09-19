var tuitionRF;
var currentUser;
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
        currentUser=res.data.Login
        console.log(currentUser);
        $('#loginPg').hide();
        $('main').css('padding-left','11.5rem')
        $('#mainPg').show();
        $('#slide-out').show();
        $('#name').text(`${currentUser.employee_id.firstname}, ${currentUser.employee_id.lastname}`)
        $('#email').text(`${currentUser.employee_id.email}`)
      })
  },

  loginCheck : function() {
    axios
      .get(
        'http://localhost:8080/Project1/login',
        {
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }
      )
      .then(function(res) {
        currentUser=res.data.Login
        console.log(currentUser);
        $('#loginPg').hide();
        $('main').css('padding-left','11.5rem')
        $('#mainPg').show();
        $('#slide-out').show();
        $('#name').text(`${currentUser.employee_id.firstname}, ${currentUser.employee_id.lastname}`)
        $('#email').text(`${currentUser.employee_id.email}`)

      })
  },
  getTRF: function() {
    axios
      .get(
        'http://localhost:8080/Project1/trf',
        {
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
      .then(function(res) {
        console.log(res.data);
      })
      .catch(function(e) {
        console.log(e);
      });
  },
  getEmployeeInfo: function() {

  },
  getEventType: function() {

  },
  getFormatType: function() {

  }
}

utils.loginCheck();

