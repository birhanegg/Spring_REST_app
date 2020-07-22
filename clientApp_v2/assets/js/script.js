 $(document).ready(function() {


     $("#formadd").submit(function(event) {

         event.preventDefault();


         var firstname = $('#firstname').val();
         var lastname = $('#lastname').val();
         var email = $('#email').val();
         console.log("firstname : " + firstname);
         console.log("lastname : " + lastname);
         console.log("email : " + email);
     });

     $('#empolyees').DataTable({
         "ajax": {
             "url": "data/test.json", //put your RESTfull controll End-point here 
             //"url": "http://localhost:9090/api/employees", 
             "dataSrc": "", //this is important since ajax response expects data:[{}]
         },
         "columns": [
             { "data": "id" },
             { "data": "firstName" },
             { "data": "lastName" },
             { "data": "email" }
         ]
     });

 });