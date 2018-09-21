var createInfoTab = function(x) {
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
                  }'>Approve</a>
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
