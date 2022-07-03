

// $.ajax({
// 	 url: url,
// 	 headers: { 
// 			'Accept': 'application/json',
// 			'Content-Type': 'application/json' 
// 		 },
// 	 type:'POST',
// 	 data: JSON.stringify(datos)
// })
// .done(function(data) {
// 	console.log(data);
// })
// .fail(function(error) {
// 	console.log(error);
// })


const url = "http:localhost:8080/ws/bancoweb/prestamos/simulacion"		


function llamadaAPI(data){
	fetch(url, {
  	method: 'POST', // or 'PUT'
  	body: JSON.stringify(data), // data can be `string` or {object}!
  	headers:{
  		'Accept': 'application/json',
    	'Content-Type': 'application/json'
  	}
	})
	.then(async res => {
		const data = await res.json()
		let render = ""
		let totalIntereses = 0
		data.map(function(elem) {
			console.log(elem)
			render += `<tr><td>${elem.numero}</td><td>${elem.cuota}</td><td>${elem.capitalInicio}</td>
			<td>${elem.interes}</td><td>${elem.abonoCapital}</td><td>${elem.saldo}</td></tr>`
			totalIntereses+=elem.interes
		})
		document.getElementById("total").innerHTML = totalIntereses
		document.getElementById("tablaAmortizacionFilas").innerHTML = render 
	})
	.catch(error => console.error('Error:', error))
}



function calcular(event) {
	event.preventDefault()
	const datos = {
		"monto":document.getElementById("monto").value,
		"plazo":document.getElementById("plazo").value,
		"interes":document.getElementById("interes").value,
		"tipo": document.getElementById("tipo_prestamo").value
	}

	llamadaAPI(datos)
}