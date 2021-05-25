$(document).ready(function() {


	var textInput = $("textarea[name=text]")
	var ratingInput = $("input[name=ratingComment]")
	var userInput = $("input[name=userComment]")
	var bookInput = $("input[name=bookComment]")

	var pasusGreska = $("p.greska")
	
	function add() {
	
		var text = textInput.val()
		var ratingComment = ratingInput.val()
		var userComment = userInput.val()
		var bookComment = bookInput.val()
	
	
		var params = {
			text: text, 
			ratingComment: ratingComment,
			userComment: userComment,
			bookComment: bookComment
		}
		
		console.log(params)
		$.post("books/AddComment", params, function(odgovor) { 
			console.log(odgovor)
	
			if (odgovor.status == "ok" || odgovor.status == "odbijen") {
			
				window.location.replace("books") 
				
			} else if (odgovor.status == "greska") {
			
				pasusGreska.text(odgovor.poruka)  
			}
		})
		console.log("POST: " + "books/AddComment")
		
		return false // sprečiti da submit forme promeni stranicu
	}
	
	$("form").eq(1).submit(add) 
	$("body").show() 
})