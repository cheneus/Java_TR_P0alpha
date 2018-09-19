document.addEventListener('DOMContentLoaded', function () {
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
    var instancesC = M.Datepicker.init(elemsC, {autoClose:'true'});
});

$(document).ready(function() {
  // $('#loginPg').hide();
  // $('#slide-out').hide();
  // $('#slide-out-icon').hide();
  // $('#mainPg').hide();
  
  
  // $('select').material_select();
  var objJsonInit;
  
  $('#addNewTR').submit( function() {
    // goes second
    alert('yeahhhhh');

  })
});

var createRowTR = function(x) {
  // var objTest = utils.getTRF();
  var objJson = x
  // objJson = [{"id":2,"dateOfEvent":"2019-05-01","timeOfEvent":"2018-09-01","date_submitted":"2018-09-17","event_address":"1 E Jackson","event_city":"Chicago","event_state":"IL","eventId":{"id":1,"name":null},"cost":350.0,"grade_format_id":{"id":21,"name":null},"submitted_by":{"id":2,"lastname":null,"firstname":null,"title":null,"supervisor":null,"birthDate":null,"hireDate":null,"dept_id":null,"address":null,"reimbursement_balance":0.0,"phone":null,"email":null},"status":{"id":0,"name":null},"event_related_attachments":"www.eventbrite.com/superjsevo","description":"Super Node Evo"},{"id":1,"dateOfEvent":"2019-12-01","timeOfEvent":"2018-09-01","date_submitted":"2018-09-17","event_address":"1 E Jackson","event_city":"Chicago","event_state":"IL","eventId":{"id":1,"name":null},"cost":350.0,"grade_format_id":{"id":21,"name":null},"submitted_by":{"id":2,"lastname":null,"firstname":null,"title":null,"supervisor":null,"birthDate":null,"hireDate":null,"dept_id":null,"address":null,"reimbursement_balance":0.0,"phone":null,"email":null},"status":{"id":0,"name":null},"event_related_attachments":"www.eventbrite.com/superjsevo","description":"Super JS Evo"}]
  console.log(objJson)
  // objArr = {};
  for(let i =0; i< objJson.length; i++) {
    // objArr[i] = objJson[i]
    var data = JSON.stringify(objJson[i])
    var rowTemplate = `<tr class="modal-trigger" href='#tableModal${i}' data-trId='${objJson[i].id}'><td>${objJson[i].id}</td>
    <td>${objJson[i].event_state}</td>
    <td>${objJson[i].cost}</td>
    <td>${objJson[i].event_id.name}</td>
    <td>${objJson[i].submitted_by.firstname}, ${objJson[i].submitted_by.lastname}</td>
    <td><i class="material-icons">check</i><i class="material-icons">close</i></td>
    </tr>`;

    var modalTemplate = `
    <div id="tableModal${i}" class="modal">
    <div class="modal-content">
      <div class="card">
      <p>"${data}"</p>
      <p>${objJson[i].date_submitted}</p>
      </div>
    </div>
    <div class="modal-footer">
      <a href="#!" class="modal-close waves-effect waves-green btn-flat">Done</a>
    </div>
    </div>`
  

  // var alternateRowTemplate = `<tr><td class='a'>{0}</td><td>SomewhatDifferent {1}</td></tr>`;
  //  ...... somewhere deep in your code
  $('#trTable_body')
    .append(rowTemplate);
  $('#modal_for_table')
    .append(modalTemplate);
    // .append(alternateRowTemplate.format('myvalue3', 'myvalue4'));
  }
  var elemsM = document.querySelectorAll('.modal');
  var instancesM = M.Modal.init(elemsM);
}

var formtest = function() {
  alert('test')
}
