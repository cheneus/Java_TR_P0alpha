var tuitionRF;

var utils = {
  currentUser:{},
  eventType:{},
  format:{},
 
  userLogin: function() {
    var user = $('#username').val();
    var pass = $('#password').val();
    console.log(`${user}, ${pass}`);
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
        currentUser = res.data.Login;
        console.log(currentUser);
        let id1 =currentUser.employee_id.id;
        let json = utils.getTRFbySI(id1);
        console.log('running SI');
        $('#loginPg').hide();
        $('main').css('padding-left', '11.5rem');
        $('#mainPg').show();
        $('#slide-out').show();
        $('#name').text(
          `${currentUser.employee_id.firstname}, ${
            currentUser.employee_id.lastname
          }`
        );
        $('#email').text(`${currentUser.employee_id.email}`);
      });
  },
  logOut: function() {
      axios
        .delete('http://localhost:8080/Project1/login', {
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
        .then(function(res) {
          console.log(res.data)
          window.location.reload();
        });
  },
   loginCheck: function() {
    axios
      .get('http://localhost:8080/Project1/login', {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
      })
      .then(function(res) {
        currentUser = res.data.Login;
        let id1 =currentUser.employee_id.id;
        let json = utils.getTRFbySI(id1);
        $('#loginPg').hide();
        $('main').css('padding-left', '11.5rem');
        $('#mainPg').show();
        $('#slide-out').show();
        $('#name').text(
          `${currentUser.employee_id.firstname}, ${
            currentUser.employee_id.lastname
          }`
        );
        $('#email').text(`${currentUser.employee_id.email}`);
      });
  },
  getTRF: function() {
    axios
      .get('http://localhost:8080/Project1/trf', {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
      })
      .then(function(res) {
        console.log(res.data);
      })
      .catch(function(e) {
        console.log(e);
      });
  },
  getTRFbySI: function(eid) {
    axios
      .get(
        'http://localhost:8080/Project1/trf/si?id='+ eid, {
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }
      )
      .then(function(res) {
        console.log(res.data);
        createRowTR(res.data);
      })
      .catch(function(e) {
        console.log(e);
      });
  },
  getTRFbyId: function(i) {
    axios
      .get('http://localhost:8080/Project1/trf/' + i, {
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
    var totalDays = $('#totalDays_form').val();
    var selectJ = $('#selectJ_form')
      .find(':selected')
      .val();
    var selectF = $('#selectF_form')
      .find(':selected')
      .val();
    var cost = $('#cost_form').val();
    var address = $('address_form').val();
    var city = $('#city_form').val();
    var state = $('#state_form').val();
    var addinfo = $('#addinfo_form').val();
    alert('alpo');
    axios
      .post(
        'http://localhost:8080/Project1/trf',
        {
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        },
        {
          data: {
            title: title,
            event_date: beginDate,
            totalDays: totalDays,
            event_type: selectJ,
            grade_format: selectF,
            cost: cost,
            event_address: address,
            event_city: city,
            event_state: state,
            addinfo: addinfo
          }
        }
      )
      .then(function(res) {
        console.log(res.data);
      });
  },
  approvedTRF: function(ap) {
    
    axios
      .put(
        'http://localhost:8080/Project1/trf/'+ap,
        {
          "status": {"id":6},
	        "id":ap
         }
        // {
        //   headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        // }
      )
      .then(function(res) {
        console.log(res.data);
      });
  },
  getEmployeeInfo: function() {},
  getEventType: function() {
    axios
    .get('http://localhost:8080/Project1/eventtype', {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
    .then(function(res) {
      console.log(res.data);
      // eventType = res.data;
      let option = res.data;
      for(let k = 0; k< option.length; k++) {
        var newOpt = `<option value="${option[k].name}" data-opId='${option[k].id}'>${option[k].name}</option>`
        $('#selectJ_form').append(newOpt);
      }
    })
    .catch(function(e) {
      console.log(e);
    });
  },
  getFormatType: function() {
    axios
    .get('http://localhost:8080/Project1/gradeformat', {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
    .then(function(res) {
      console.log(res.data);
           // format= res.data;
      let option = res.data;
      for(let k = 0; k< option.length; k++) {
        var newOpt = `<option value="${option[k].name}" data-opId='${option[k].id}'>${option[k].name}</option>`
        $('#selectF_form').append(newOpt);
      }
    })
    .catch(function(e) {
      console.log(e);
    });
  },

};

var webCtrl = {
  TRFbyId: function(x) {},
  TRFbySI: function(x) {
    console.log('running SI');
    var json = utils.getTRFbySI(x);
    createRowTR(json);
  }
};

utils.loginCheck();

utils.getEventType();
utils.getFormatType();
// utils.getTRFbySI(2);