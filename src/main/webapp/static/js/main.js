document.addEventListener('DOMContentLoaded', function() {
  var elems = document.querySelectorAll('.sidenav');
  var instances = M.Sidenav.init(elems);
  // var instances = M.Sidenav.init(elems, options);
  var elemsM = document.querySelectorAll('.modal');
  var instancesM = M.Modal.init(elemsM);
  // var instancesM = M.Modal.init(elemsM, options);
  var elemsF = document.querySelectorAll('select');
  var instancesF = M.FormSelect.init(elemsF);
  // var instancesF = M.FormSelect.init(elems, options);
  var elemsC = document.querySelectorAll('.datepicker');
  // var instancesC = M.Datepicker.init(elemsC, options);
  var instancesC = M.Datepicker.init(elemsC, {
    autoClose: 'true',
    format: 'dd-mmm-yy'
  });
});

$(document).ready(function() {
  // $('#loginPg').hide();
  // $('#slide-out').hide();
  // $('#slide-out-icon').hide();
  // $('#mainPg').hide();

  // $('select').material_select();

  $(document).on('click', '#trCheck', function(event) {
    var tag = $(this).data('trid');
    var status = $(this).data('trstat');
    var cost = $(this).data('cost');
    if (status === 5) {
      utils.updateEmployeeBalance(cost, tag);
    }
    console.log($(this).data('trid'));
    utils.approvedTRF(tag, status);
  });

  $(document).on('click', '#trInfoReq', function(event) {
    var tag = $(this).data('trid');
    var status = $(this).data('trstat');
    var target = $(this).data('user');
    console.log(target);
    utils.postInfoReq(tag, target);
  });

  $('#loginBtn').on('click', function() {
    utils.userLogin();
    console.log(`loggged = ${currentUser} `);
  });

  var objJsonInit;
  $('#navLogout').click(function() {
    console.log(`logging out`);
    utils.logOut();
  });
  $('#sidebar_add').click(function() {
    var elemsF = document.querySelectorAll('select');
    var instancesF = M.FormSelect.init(elemsF);
  });

  $('#sidebar_viewmine').click(function() {
    // createRowTR(tuitionRF_filtered);
    utils.getMyTRF(currentUser.id);
  });

  $('#sidebar_viewall').click(function() {
    createRowTR(tuitionRF);
    // utils.getTRFmgr(currentUser.id, currentUser.employee_id.dept_id.id);
  });

  $('#sidebar_viewNA').on('click', function() {
    createRowTR(tuitionRFSI);
    // utils.getTRFmgrSI(currentUser.id, currentUser.employee_id.dept_id.id);
  });
  $('#sidebar_my').on('click', function() {
    utils.getAddress(currentUser.employee_id.address.id);
  });

  $('#submit_form').on('click', function() {
    // $('#addNewTR').valid()
    utils.postTRF();
  })

  $('#addNewTR').submit(function() {
    // goes second
  });

  $('#addNewTR').validate({
    rules: {
      uname: {
        required: true,
        minlength: 5
      },
      cemail: {
        required: true,
        email: true
      },
      password: {
        required: true,
        minlength: 5
      },
      cpassword: {
        required: true,
        minlength: 5,
        equalTo: '#password'
      },
      title_form: {
        required: true,
        minlength: 6
      },
      selectF_form: {
        required: true
      },
      selectJ_form: {
        required: true
      },
      beginDate_form: {
        required: true
      },
      totalDays_form: {
        required: true
      },
      cost_form: {
        required: true
      },
      event_address: {
        required: true
      },
      event_city: {
        required: true
      },
      event_state: {
        required: true
      },
      //For custom messages
      messages: {
        uname: {
          required: 'Enter a username',
          minlength: 'Enter at least 5 characters'
        },
        cost_form: {
          required: 'Please enter an amount'
        }
      },
      errorElement: 'div',
      errorPlacement: function(error, element) {
        var placement = $(element).data('error');
        if (placement) {
          $(placement).append(error);
        } else {
          error.insertAfter(element);
        }
      }
    }
  });
});
