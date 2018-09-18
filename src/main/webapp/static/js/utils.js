var tuitionRF;

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
        $('#loginPg').hide();
        $('main').css('left-padding','11.5rem')
        $('#mainPg').show();
        $('#slide-out').show();
        $('#name').text()
        $('#email').text()

      })
      .catch(function(e) {
        $('#login_feedback').text("Wrong Username/Password");
        $('#login_feedback').css('display', 'block');
        console.log(e);
      });
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



