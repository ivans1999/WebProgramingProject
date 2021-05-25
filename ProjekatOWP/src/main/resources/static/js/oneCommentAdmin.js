var id = window.location.search.slice(1).split('&')[0].split('=')[1]; // čitanje vrednosti prvog parametra GET zahteva na ovu stranicu (id-a u ovom slučaju)

$(document).ready(function() {
	


	var formaIzmena = $("form").eq(0)
	var tabelaZaObicanPrikaz = $("table.forma").eq(0)
	
	

	function comm() {
	
		var params = {
			id: id
		}

		console.log(params)
		
		$.get("Comments/OneCommentAdminDetails", params, function(odgovor) { 
		
			console.log(odgovor)
	
			if (odgovor.status == "ok") {
			
				
				
				var comment = odgovor.comment
				
				var date1 = comment.dateComment
				var date2 = new Date(date1);
				var date3 = date2.getDate()  + '/' + date2.getMonth() + '/' +  date2.getFullYear();
				
				console.log(date3);
	
				// popuni formu za izmenu
				
				formaIzmena.find("input[name=id]").val(comment.id)
				formaIzmena.find("input[name=text]").val(comment.text)
				formaIzmena.find("input[name=ratingComment]").val(comment.rating)
				formaIzmena.find("input[name=dateComment]").val(date3)
				formaIzmena.find("input[name=bookComment]").val(comment.bookComment.name)
				formaIzmena.find("input[name=userComment]").val(comment.userComment.username)
				formaIzmena.find("input[name=statusComment]").val(comment.statusComment)
				
				
				

				
			}
		})
		console.log("GET: " + "Comments/OneCommentAdminDetails")
		
	}
	comm()
	
	formaIzmena.submit(function() {

		var statusCommentInput = formaIzmena.find("input[name=statusComment]")
		var idInput = formaIzmena.find("input[name=id]")
		
		var statusComment = statusCommentInput.val()
		var id = idInput.val()
		
			
		var params = {
			id: id, 
			statusComment: statusComment
			
		}
		console.log(params)
		
		
		$.post("Comments/EditOneCommentAdmin", params, function(odgovor) {
		
			console.log(odgovor)

			if (odgovor.status == "ok" || odgovor.status == "odbijen") {
			
				window.location.replace("Comments/ShowCommentsAdmin")
				
			} else if (odgovor.status == "greska") {
			
				pasusGreska.text(odgovor.poruka)
			}
		})
		console.log("POST: Comments/EditOneCommentAdmin")

		return false
	})
	
	$("body").show()  // prikazi stanicu nakon sto se preuzmu podaci
	
})

