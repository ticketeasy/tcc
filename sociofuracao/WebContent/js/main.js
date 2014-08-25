function hideBlockContainerFlashMember(){
	$("#block_erro_member").hide();  

}

function afterLoad(xhr, status, args) {
		if(args.msg_erro == 1){
			$("#block_erro").show(); 
			$("#identificacao").hide(); 
			$("#msgerro").html('Digitar usuário ou Senha!');
	}else if(args.msg_erro == 2){
		$("#block_erro").show(); 
			$("#identificacao").hide(); 
			$("#msgerro").html('Usuario ou senha não se encontra no sistema!');
	}
 }
function hideBlockContainerFlash(){
		$("#block_erro").hide();    
		
}



