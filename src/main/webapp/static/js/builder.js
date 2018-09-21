var createInfoTab = function(x) {
    if (x === "" || x === undefined) {
        return false;
    }
  // $('#trTable_body').empty();
  // $('#modal_for_table').empty();
  for (let i = 0; i < x.length; i++) {
    var colTemplate = `
    <ul class="collapsible">
    <li class="">
        <div class="collapsible-header" tabindex="0"><i class="material-icons">filter_drama</i>
            Requestor : ${x[i].requestorId.firstname}, ${
            x[i].requestorId.lastname
            }
        </div>
        <div class="collapsible-body" style="">
            <div class="row">
                <div class="col s12 m4">
                    <h5 class="heading-tertiary">Event Address:</h5>
                    <p>${x[i].formRef.event_address}<br>
                        ${x[i].formRef.event_city} ,${x[i].formRef.event_state}</p><br>
                    <h5 class="heading-tertiary">Cost:</h5>
                    <p>${x[i].formRef.cost}</p> <br>
                </div>

                <div class="col s12 m4">
                    <h5 class="heading-tertiary">Title:</h5>
                    <p>${x[i].formRef.title}</p> <br>
                    <h5 class="heading-tertiary">Justification:</h5>
                    <p>${x[i].formRef.eventId.id}</p><br>
                </div>
                <div class="col s12 m4">
                    <h5 class="heading-tertiary">Date of Event:</h5>
                    <p>${x[i].formRef.eventDate}</p><br>
                    <h5 class="heading-tertiary">Total Days:</h5>
                    <p></p>${x[i].formRef.totalDays}<p></p><br>
                    <h5 class="heading-tertiary">Date Submitted:</h5>
                    <p>${x[i].formRef.dateSubmitted}</p>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <textarea id="addinfo_form" class="materialize-textarea" length="300" placeholder="ie. website, email, etc..."></textarea>
                    <label for="addinfo_form">Please provide addtional Information</label>
                </div>
                <div class="col s12">
                <a class="btn waves-effect green waves-green" id="trCheck" data-trstat='2' data-trId='${
                    x[i].id
                  }'>Send</a>
                </div>
            </div>
        </div>
    </li>
</ul>
`;

    // var alternateRowTemplate = `<tr><td class='a'>{0}</td><td>SomewhatDifferent {1}</td></tr>`;
    //  ...... somewhere deep in your code
    $('#infoReqTabs').append(colTemplate);
    // .append(alternateRowTemplate.format('myvalue3', 'myvalue4'));
  }
  var elemsT = document.querySelectorAll('.collapsible');
  var instancesT = M.Collapsible.init(elemsT, { accordion: 'true' });
};

var TR_final = function() {

}

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
      var rowTemplate = `<tr class="modal-trigger status-${
        objJson[i].status.id
      }" href='#tableModal${i}'>
      <td>${objJson[i].id}</td>
      <td>${objJson[i].title}</td>
      <td>${objJson[i].event_state}</td>
      <td>${objJson[i].cost}</td>
      <td>${objJson[i].eventId.name}</td>
      <td>${objJson[i].submittedBy.firstname}, ${
        objJson[i].submittedBy.lastname
      }</td>
      </tr>`;
  
      var modalTemplate = `
      <div id="tableModal${i}" class="modal">
        <div class="modal-content">
          <div class="card-panel">
            <div class="row">
                 <div class="col s12 m4">
                 <h3 class="heading-tertiary">Event Address:</h3>
                      <p>${objJson[i].event_address}<br>
                      ${objJson[i].event_city} ,${objJson[i].event_state}</p><br>
                 <h3 class="heading-tertiary">Submitted by:</h3> <p>${
                   objJson[i].submittedBy.firstname
                 }, ${objJson[i].submittedBy.lastname}</p> <br>
                 </div>
                
                 <div class="col s12 m4">
                 <h3 class="heading-tertiary">Format:</h3> <p>${
                   objJson[i].gradeFormat.name
                 }</p> <br>
                 <h3 class="heading-tertiary">Justification:</h3> <p>${
                   objJson[i].eventId.name
                 }</p><br>
                 <h3 class="heading-tertiary">Status:</h3> <p>${
                   objJson[i].status.name
                 }</p>
                 </div>
                 <div class="col s12 m4">
                 <h3 class="heading-tertiary">Date of Event:</h3> <p>${
                   objJson[i].eventDate
                 }</p><br>
                 <h3 class="heading-tertiary">Total Days:</h3> <p></p>${objJson[i].totalDays}</p><br>
                 <h3 class="heading-tertiary">Date Submitted:</h3> <p>${
                   objJson[i].dateSubmitted
                 }</p>
                </div>
            </div>
            <div class="card-action right-align">
              <a class="btn modal-close waves-effect green waves-green" id="trCheck" data-trstat='2' data-trId='${
                objJson[i].id
              }'>Approve</a>
              <a class="btn waves-effect orange darken-1 waves-yellow" id="trInfoReq" data-user='${objJson[i].submittedBy.id}' data-trstat='4' data-trId='${
                objJson[i].id
              }'>RequestInfo</a>     
              <a class="btn modal-close waves-effect red waves-red" id="trCheck" data-cost=${objJson[i].cost} data-trstat='5' data-trId='${
                objJson[i].id
              }'>Deny</a>     
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
  