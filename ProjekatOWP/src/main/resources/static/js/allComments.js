var bookId = window.location.search.slice(1).split('&')[0].split('=')[1]; // čitanje vrednosti prvog parametra GET zahteva na ovu stranicu (id-a u ovom slučaju)

$(document).ready(function() {
	

		
	var tabelaKomentari = $("table#komentari")
	var tabela = $("table.komentari1")
	
	
	
	function allComments() {
	
		var params = {
			bookId: bookId,
		
			
			
		}
		console.log(params)
		
		
		// traži od server-a komentare
		
		$.get("books/AllComments", params, function(odgovor) {
			console.log(odgovor)

			if (odgovor.status == "ok") {
				/*var tabela = $("table.komentari1").eq(0)*/
				tabela.find("tr:gt(1)").remove()
	
					
					
					var komentari = odgovor.comments	
					for (var it in komentari) { // za svaki kom
					
					
						var date1 = komentari[it].dateComment
						var date2 = new Date(date1);
						var date3 = date2.getDate()  + '/' + date2.getMonth() + '/' +  date2.getFullYear();
					
						tabela.append( 
						
								'<li class="komLI">' + 
								
								'<span class="kom1">' + "User: " +  komentari[it].userComment.username +  '</span>' +
								'<br>' +
								'<span class="kom2">' +  komentari[it].text +  '</span>' +
								'<br>' +
								'<span class="kom1">' + "Date: "  +  date3 +  '</span>' +
								'<br>' +
								'<span class="kom1">' + "Rating: " + komentari[it].rating + ' <i class="em em-star" aria-role="presentation" aria-label="WHITE MEDIUM STAR"></i>' + '</span>' +
								'</li>'
						)
					}
					tabelaKomentari.show();
				} else {
					tabelaKomentari.hide() // sakri tabelu ako nema posećenih filmova
				}
			}
		)
		console.log("GET: " + "books/AllComments")
	}


	allComments()
	
	
	$("body").show() // prikazi stanicu nakon sto se preuzmu podaci
})