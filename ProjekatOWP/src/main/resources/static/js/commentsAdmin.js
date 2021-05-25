$(document).ready(function() {
	
		
	var tabelaKomentari = $("table#komentari")
	var tabela = $("table.komentari1")
	
	
	
	function commentsAdmin() {
	
		
		$.get("Comments/CommentsAdmin", function(odgovor) {
		
			console.log(odgovor)

			if (odgovor.status == "ok") {
	
				tabela.find("tr:gt(1)").remove()
		
	
					var komentari = odgovor.comments
					
				
					for (var it in komentari) { 
					
					var date1 = komentari[it].dateComment
					var date2 = new Date(date1);
					var date3 = date2.getDate()  + '/' + date2.getMonth() + '/' +  date2.getFullYear();
				
					
					
						tabela.append(
						
								'<li class="komLI"><a href="Comments/OneCommentAdmin?id=' + komentari[it].id + '">' + 
								
								'<span class="kom1">' + "User Username --> " +  komentari[it].userComment.username +  '</span>' +
								'<br>' +
								'<span class="kom1">' + "Book Name --> " +  komentari[it].bookComment.name +  '</a></span>' +
								'<br>' +
								'<span class="kom2">' + "Comment Text --> " + komentari[it].text +  '</span>' +
								'<br>' +
								'<span class="kom1" id="kom3">' + "Date --> "  +  date3 +  '</span>' +
								'<br>' +
								'<span class="kom1">' + "Rating --> " + komentari[it].rating + ' <i class="em em-star" aria-role="presentation" aria-label="WHITE MEDIUM STAR" style="width:18px"></i>' + '</span>' +
								'<br>' +
								'<b class="kom1">' + "Is it approved? --> " + komentari[it].statusComment + '</b>' +
								
								'<br><br>'+
								
								
								'</a></li >'
						)
					}
					
					tabelaKomentari.show();
				} else {
					tabelaKomentari.hide()
				}
			}
		)
		
	}


	commentsAdmin()
	
	
	$("body").show() 
})
