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
        webCtrl.TRFbySI(currentUser.employee_id.id);
        console.log("running SI");
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
        webCtrl.TRFbySI(currentUser.employee_id.id);
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
  getTRFbySI: function(id) {
    axios
      .get(
        'http://localhost:8080/Project1/trf/si',
        {
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        },{
          params: {
            id: id
          }
        })
      .then(function(res) {
        console.log(res.data);
        return res.data;
      })
      .catch(function(e) {
        console.log(e);
      });
  },
  getTRFbyId: function(i) {
    axios
      .get(
        'http://localhost:8080/Project1/trf/'+i,
        {
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
      .then(function(res) {
        console.log(res.data);
        return res.data;
      })
      .catch(function(e) {
        console.log(e);
      });
  },
  postTRF: function() {
    var title = $('#title_form').val();
    var beginDate = $('#beginDate_form').val();
    var totalDays =$('#totalDays_form').val();
    var selectJ = $('#selectJ_form').find(":selected").val();
    var selectF = $('#selectF_form').find(":selected").val();
    var cost = $('#cost_form').val();
    var address = $('address_form').val();
    var city =$('#city_form').val();
    var state = $('#state_form').val();
    var addinfo =$('#addinfo_form').val();
    alert('alpo')

    axios
      .post(
        'http://localhost:8080/Project1/trf',
        {
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        },
        {
          body: {
            title: title,
            event_date: beginDate,
            totalDays:totalDays,
            event_type: selectJ,
            grade_format: selectF,
            cost: cost,
            event_address:address,
            event_city:city,
            event_state:state,
            addinfo:addinfo
          }
        }
      )
      .then(function(res) {
        console.log(res.data)

      })
  },
  getEmployeeInfo: function() {

  },
  getEventType: function() {

  },
  getFormatType: function() {

  }
}

var webCtrl = {
  TRFbyId : function(x) {
    
  },
  TRFbySI : function(x) {
    console.log("running SI");
    var json = utils.getTRFbySI(x);
    createRowTR(json);
  }
}

utils.loginCheck();

