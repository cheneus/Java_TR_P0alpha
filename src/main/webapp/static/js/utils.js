var tuitionRF;
var currentUser;
var utils = {
  user:{},
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
        let json = utils.getTRFbySI(currentUser);
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
  getTRFbyEId: function() {
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
  // using status and eid
  getTRFbySI: function(user) {
    var eid = user.id;
    var deptId = user.employee_id.dept_id.id || 0;
    axios
      .get(
        'http://localhost:8080/Project1/trf/si?id='+ eid, 
        {
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        },
        {
          params: {
            'id':eid,
            'deptId':deptId
          }
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
  getMyTRF: function(eid) {
    axios
      .get(
        'http://localhost:8080/Project1/trf/my?id='+ eid, {
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
    var eid = currentUser.id;
    var title = $('#title_form').val();
    var beginDate = $('#beginDate_form').val();
    var totalDays = $('#totalDays_form').val();
    var selectJ = $('#selectJ_form')
      .find(':selected')
      .data('opid');
    var selectF = $('#selectF_form')
      .find(':selected')
      .data('opid');
    var cost = $('#cost_form').val();
    var address = $('#address_form').val();
    var city = $('#city_form').val();
    var state = $('#state_form').val();
    var addinfo = $('#addinfo_form').val();
    axios
      .post(
        'http://localhost:8080/Project1/trf',        
        {
          title: title,
          eventDate: beginDate,
          totalDays: totalDays,
          eventId: selectJ,
          gradeFormat: selectF,
          cost: cost,
          event_address: address,
          event_city: city,
          event_state: state,
          addinfo: addinfo,
          submittedBy:eid
      },
      {
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
      })
      .then(function(res) {
        console.log(res.data);
        window.location.reload();
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
      )
      .then(function(res) {
        console.log(res.data);
        utils.getTRFbySI(currentUser.employee_id.id);
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

var createRowTR = function(x) {
  $('#trTable_body').empty();
  $('#modal_for_table').empty();
  // var objTest = utils.getTRF();
  var objJson = x;
  // objJson = [{"id":2,"dateOfEvent":"2019-05-01","timeOfEvent":"2018-09-01","date_submitted":"2018-09-17","event_address":"1 E Jackson","event_city":"Chicago","event_state":"IL","eventId":{"id":1,"name":null},"cost":350.0,"grade_format_id":{"id":21,"name":null},"submitted_by":{"id":2,"lastname":null,"firstname":null,"title":null,"supervisor":null,"birthDate":null,"hireDate":null,"dept_id":null,"address":null,"reimbursement_balance":0.0,"phone":null,"email":null},"status":{"id":0,"name":null},"event_related_attachments":"www.eventbrite.com/superjsevo","description":"Super Node Evo"},{"id":1,"dateOfEvent":"2019-12-01","timeOfEvent":"2018-09-01","date_submitted":"2018-09-17","event_address":"1 E Jackson","event_city":"Chicago","event_state":"IL","eventId":{"id":1,"name":null},"cost":350.0,"grade_format_id":{"id":21,"name":null},"submitted_by":{"id":2,"lastname":null,"firstname":null,"title":null,"supervisor":null,"birthDate":null,"hireDate":null,"dept_id":null,"address":null,"reimbursement_balance":0.0,"phone":null,"email":null},"status":{"id":0,"name":null},"event_related_attachments":"www.eventbrite.com/superjsevo","description":"Super JS Evo"}]
  console.log(objJson);
  // objArr = {};
  for (let i = 0; i < objJson.length; i++) {
    // objArr[i] = objJson[i]
    var data = JSON.stringify(objJson[i]);
    var rowTemplate = `<tr class="modal-trigger" href='#tableModal${i}'>
    <td>${objJson[i].id}</td>
    <td>${objJson[i].title}</td>
    <td>${objJson[i].event_state}</td>
    <td>${objJson[i].cost}</td>
    <td>${objJson[i].eventId.name}</td>
    <td>${objJson[i].submittedBy.firstname}, ${objJson[i].submittedBy.lastname}</td>
    </tr>`;

    var modalTemplate = `
    <div id="tableModal${i}" class="modal">
      <div class="modal-content">
        <div class="card-panel">
          <div class="row">
               <div class="col s12 m4">
               <h3 class="heading-tertiary">Event Address:</h3>
                    <p>${objJson[i].event_address}<br>
                    ${objJson[i].event_city} ,${objJson[i].event_state}</p>
               <h3 class="heading-tertiary">Submitted by:</h3> <p>${objJson[i].submittedBy.firstname}, ${objJson[i].submittedBy.lastname}</p> <br>
               </div>
              
               <div class="col s12 m4">
               <h3 class="heading-tertiary">Format:</h3> <p>${objJson[i].gradeFormat.name}</p> <br>
               <h3 class="heading-tertiary">Justification:</h3> <p>${objJson[i].eventId.name}</p><br>
               <h3 class="heading-tertiary">Status:</h3> <p>${objJson[i].status.name}</p>
               </div>
               <div class="col s12 m4">
               <h3 class="heading-tertiary">Date of Event:</h3> <p>${objJson[i].eventDate}</p><br>
               <h3 class="heading-tertiary">Total Days:</h3> <p>${objJson[i].totalDays}</p><br>
               <h3 class="heading-tertiary">Date Submitted:</h3> <p>${objJson[i].dateSubmitted}</p>
              </div>
          </div>
          <div class="card-action right-align">
            <a class="btn modal-close waves-effect green waves-green" id="trCheck"  data-trId='${objJson[i].id}'>Approve</a>
            <a class="btn modal-close waves-effect red waves-red" id="trCheck"  data-trId='${objJson[i].id}'>Deny</a>     
          </div>
        </div>
      </div>
    </div>`;

    // var alternateRowTemplate = `<tr><td class='a'>{0}</td><td>SomewhatDifferent {1}</td></tr>`;
    //  ...... somewhere deep in your code
    $('#trTable_body').append(rowTemplate);
    $('#modal_for_table').append(modalTemplate);
    // .append(alternateRowTemplate.format('myvalue3', 'myvalue4'));
  }
  var elemsM = document.querySelectorAll('.modal');
  var instancesM = M.Modal.init(elemsM);
};


utils.loginCheck();
utils.getEventType();
utils.getFormatType();
// utils.getTRFbySI(2);