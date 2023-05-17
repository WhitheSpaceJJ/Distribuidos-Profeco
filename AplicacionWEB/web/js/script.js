function login(){
    var user, password

    user = document.getElementById("usuario").value;
    password= document.getElementById("contra").value;

    if(user=="nalleprueba" && password=="123456"){
        //console.log("Haz inicado sesión!!")
        alert("Haz iniciado sesión!!")
    }else{
        alert("Datos incorrectos")
    }
}
