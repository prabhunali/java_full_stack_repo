$(document).ready(function() {
    $('.alert').alert();

    $('#viewDetails').click(function() {
        alertMessage("Jquery alert method from an external file is working");
    });

    $('#mentorFirstName').change(function(){
        if ($(this).val())
        {
          $("#btnSaveMentorBasicInfo").removeAttr('disabled');
        }
      });
});

function alertMessage(message) {
    window.alert(message);
}

function toggleElement(elId) {
    $('#'+this.id).click(function() {
        $('#' + elId).toggle("slide");
      });
}

function getTableRowIndex(tableId) {
    let rowIndex;
    $('#' + tableId).find('tr').click( function(){
        rowIndex =($(this).index()+1);
        //alert('You clicked row '+ ($(this).index()+1) );
    });

    return rowIndex
}