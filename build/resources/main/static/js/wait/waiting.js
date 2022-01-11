//============================================================================
//선언
//============================================================================
var WaitingDialogLoadCount = 0;
var LocalPath = "/resources/js/wait/"
function WaitingDialog(initObj,CreateObj)
{
	//property
	this.m_OpenObject = null;		//Dialog object
	this.m_CreateObjectStr = CreateObj;		//Dialog Create object string
	this.m_OpenType = (initObj != null && typeof initObj.OpenType == "string")? initObj.OpenType : "2";				//Dialog 타입 1: 새창, 2:레이어
	//메시지 박스 설정
	this.m_Msg = (initObj != null && typeof initObj.Msg == "string")? initObj.Msg :"진행중입니다.잠시만 기다려 주세요";					//메시지
	this.m_PressImageUrl = (initObj != null && typeof initObj.PressImageUrl == "string")? initObj.PressImageUrl :LocalPath+"/imgLoadingBar.gif";			//진행바 이미지 경로
	this.m_MsgBoxWidth = (initObj != null && typeof initObj.MsgBoxWidth == "string")? initObj.MsgBoxWidth :"500";			//메시지 박스 width
	this.m_MsgBoxHeight = (initObj != null && typeof initObj.MsgBoxHeight == "string")? initObj.MsgBoxHeight :"400";			//메시지 박스 height
	this.m_MsgBoxBackGroundColor = (initObj != null && typeof initObj.MsgBoxBackGroundColor == "string")? initObj.MsgBoxBackGroundColor :"747474";	//메시지 박스 배경색
	this.m_MsgBoxBackImageUrl = (initObj != null && typeof initObj.MsgBoxBackImageUrl == "string")? initObj.MsgBoxBackImageUrl :"";		//메시지 박스 배경이미지경로  
	this.m_MsgBoxFont = (initObj != null && typeof initObj.MsgBoxFont == "string")? initObj.MsgBoxFont :"돋움";		//메시지 박스 배경이미지경로
	this.m_MsgBoxFontSize = (initObj != null && typeof initObj.MsgBoxFontSize == "string")? initObj.MsgBoxFontSize :"9pt";		//메시지 박스 배경이미지경로
	this.m_MsgBoxFontColor = (initObj != null && typeof initObj.MsgBoxFontColor == "string")? initObj.MsgBoxFontColor :"FFFFFF";		//메시지 박스 배경이미지경로
	this.m_MsgBoxBorderColor = (initObj != null && typeof initObj.MsgBoxBorderColor == "string")? initObj.MsgBoxBorderColor :"FFFFFF";		//메시지 박스 배경이미지경로
	//Blind 설정
	this.m_BlindColor = (initObj != null && typeof initObj.BlindColor == "string")? initObj.BlindColor :"000000";				//배경색
	this.m_BlindAlpha = (initObj != null && typeof initObj.BlindAlpha == "string")? initObj.BlindAlpha :"10";				//Blind 투명도
	this.m_count =  WaitingDialogLoadCount++; //레이어 증가수

	//사용유무 설정
	this.m_PressUseYN = (initObj != null && typeof initObj.PressUseYN == "boolean")? initObj.PressUseYN :false;				//진행바 사용여부
	this.m_BlindUseYN = (initObj != null && typeof initObj.BlindUseYN == "boolean")? initObj.BlindUseYN :false;				//Blind 사용여부
	this.m_MsgUseYN = (initObj != null && typeof initObj.MsgUseYN == "boolean")? initObj.MsgUseYN :true;				//메시지 사용여부
	this.m_CloseTimeUseYN = (initObj != null && typeof initObj.CloseTimeUseYN == "boolean")? initObj.CloseTimeUseYN :true;			//자동 종료 사용여부
	this.m_CloseBtnUseYN = (initObj != null && typeof initObj.CloseBtnUseYN == "boolean")? initObj.CloseBtnUseYN :false;			
	this.m_CloseTime = (initObj != null && typeof initObj.CloseTime == "string")? initObj.CloseTime :"3000";				//Dialog 자동 종료 시간 1000 = 1초

	//method
	this.OnOpenWait = OnOpenWait;			//Dialog 열기
	this.OnCloseWait = OnCloseWait;		//Dialog 닫기
	this.WinBodyHTML = WinBodyHTML;	//메시지 내용
	this.InitMsgLayer = InitMsgLayer;
	this.InitBlindLayer = InitBlindLayer; 
	this.WT_LayerCenter = WT_LayerCenter; //레이어 중앙 정렬
	this.CreateLayer	= CreateLayer; //기본 레이어 생성
	this.OnOpenWindow	= OnOpenWindow; //레이어창
	this.TimeID = null;
	this.TimeScroll = null;
	//초기생성
	this.CreateLayer();

}

function CreateLayer()
{
	//메시지 
	var IsWTLayer = document.all["_WT_FLHVIEWLayer_"+this.m_count+""];

	if(typeof IsWTLayer != "object")
	{	
		//document.body.insertAdjacentHTML("BeforeEnd" ,this.InitMsgLayer()) ;
		document.write(this.InitMsgLayer());
	}

	//블라인드
	var IsWTBlindLayer = document.all["_WT_BlindVIEWLayer"];
	if(typeof IsWTBlindLayer != "object")
		document.write(this.InitBlindLayer());
//		document.body.insertAdjacentHTML("BeforeEnd" ,this.InitBlindLayer()) ;

}
function OnCloseWait()
{
		clearInterval(this.TimeID);
		clearInterval(this.TimeScroll);
	//try{

		if (this.m_OpenType == "2")//레이어
		{
			var IsWTLayer = document.all["_WT_FLHVIEWLayer_"+this.m_count+""];
			IsWTLayer.style.width = "0px";
			IsWTLayer.style.height = "0px";

/*
			var EditorDoc	=	this.m_OpenObject.contentWindow.document;
			EditorDoc.open();
			EditorDoc.write(this.WinBodyHTML());
			EditorDoc.close();

*/
try{
			this.m_OpenObject.src="about:blank";
			this.m_OpenObject = null;
}catch(e){}
		}
		else	//새창
		{
			this.m_OpenObject.close(); 
			this.m_OpenObject = null;
		}
 
		//블라인드
		var IsWTBlindLayer = document.all["_WT_BlindVIEWLayer"];
		IsWTBlindLayer.style.display = "none";
		IsWTBlindLayer.style.width = "0";
		IsWTBlindLayer.style.height = "0";


	//}catch(e){}

}

function OnOpenWait(inData)
{
	//이미 실행중인 경우 처리 안함
	if(this.m_OpenObject != null)
		return;
	if (typeof inData == "string" )
		this.m_Msg = inData;
	if (this.m_OpenType == "2")//레이어
	{
	
		//중앙 위치
		var rtnObj = new Object();
		var IsWTLayer = document.all["_WT_FLHVIEWLayer_"+this.m_count+""];

		rtnObj.y = parseInt(parseInt(document.documentElement.scrollTop) +(parseInt(document.documentElement.clientHeight)-parseInt(this.m_MsgBoxHeight))/2,0) ; 
		rtnObj.x = parseInt(parseInt(document.documentElement.scrollLeft) +(parseInt(document.documentElement.clientWidth)-parseInt(this.m_MsgBoxWidth))/2,0);



		IsWTLayer.style.left= rtnObj.x +'px';
		IsWTLayer.style.top = rtnObj.y +'px';

		this.WT_LayerCenter();

		this.m_OpenObject = document.all["_WT_MSGVIEWIfr_"+this.m_count+""];
		try{
		var EditorDoc	=	this.m_OpenObject.contentWindow.document;
		EditorDoc.open();
		EditorDoc.write(this.WinBodyHTML());
		EditorDoc.close();
		}catch(e){}
//		this.m_OpenObject.style.display =  "block";
	}
	else	//새창
	{
		this.m_OpenObject = window.showModelessDialog(LocalPath + "loading.htm",this,"dialogHeight:"+ this.m_MsgBoxHeight +"px;dialogWidth:"+ this.m_MsgBoxWidth +"px;scroll:0;center:1;status:0;help:no;");
		try{
		var EditorDoc	=	this.m_OpenObject.document;
		EditorDoc.open();
		EditorDoc.write(this.WinBodyHTML());
		EditorDoc.close();
		}catch(e){}
	}

	if(this.m_BlindUseYN )//Blind
	{
		var IsWTBlindLayer = document.all["_WT_BlindVIEWLayer"];
		if (typeof IsWTBlindLayer == "object")
		{

			IsWTBlindLayer.style.width=parseInt(document.documentElement.scrollWidth)   + "px";
			IsWTBlindLayer.style.height=parseInt(document.documentElement.scrollHeight) + "px"; //(document.body.scrollHeight) + "px";
			 if (window.addEventListener){
			window.addEventListener("onresize",function (){OnBlindReSize("_WT_BlindVIEWLayer") }, true);
			 }
			 else{
			window.attachEvent("onresize",function (){OnBlindReSize("_WT_BlindVIEWLayer") });
			 }
			IsWTBlindLayer.style.display =  "block";
		}
	}

	if((this.m_CloseTimeUseYN && this.m_CloseTime != "" && this.m_CreateObjectStr != "") || (typeof inData == "number" )) //자동 닫힘
	{	
		if ( typeof inData == "number")
			this.TimeID = setInterval(this.m_CreateObjectStr + ".OnCloseWait()",inData);
		else
			this.TimeID = setInterval(this.m_CreateObjectStr + ".OnCloseWait()",this.m_CloseTime);
	}

}

function OnOpenWindow(inURL)
{

	//이미 실행중인 경우 처리 안함
	if(this.m_OpenObject != null)
		return;

	if (this.m_OpenType == "2")//레이어
	{
		var IsWTLayer = document.all["_WT_FLHVIEWLayer_"+this.m_count+""];
		IsWTLayer.style.width =this.m_MsgBoxWidth + 'px';
		IsWTLayer.style.height =this.m_MsgBoxHeight + 'px';
		//중앙 위치
		var rtnObj = new Object();
		var H = screen.availHeight;
		var W = screen.availWidth;
		rtnObj.y = parseInt(parseInt(document.documentElement.scrollTop) +(parseInt(document.documentElement.clientHeight)-parseInt(this.m_MsgBoxHeight))/2,0) ; 
		rtnObj.x = parseInt(parseInt(document.documentElement.scrollLeft) +(parseInt(document.documentElement.clientWidth)-parseInt(this.m_MsgBoxWidth))/2,0);



		IsWTLayer.style.left= rtnObj.x +'px';
		IsWTLayer.style.top = rtnObj.y +'px';
		this.WT_LayerCenter();

		this.m_OpenObject = document.all["_WT_MSGVIEWIfr_"+this.m_count+""];
		if(inURL != "")
		{
			try{
			this.m_OpenObject.src=inURL;
			}catch(e){}
		}

	}
	else	//새창
	{
		this.m_OpenObject = window.showModelessDialog(inURL,this,"dialogHeight:"+ this.m_MsgBoxHeight +"px;dialogWidth:"+ this.m_MsgBoxWidth +"px;scroll:0;center:1;status:0;help:no;");
	}

	if(this.m_BlindUseYN )//Blind
	{
		var IsWTBlindLayer = document.all["_WT_BlindVIEWLayer"];
		if (typeof IsWTBlindLayer == "object")
		{

			IsWTBlindLayer.style.width=parseInt(document.documentElement.scrollWidth)   + "px";
			IsWTBlindLayer.style.height=parseInt(document.documentElement.scrollHeight)  + "px";
			
			IsWTBlindLayer.style.display =  "block";
			
			if (window.addEventListener){
			window.addEventListener("onresize",function (){OnBlindReSize("_WT_BlindVIEWLayer") }, true);
			 }
			 else{
			window.attachEvent("onresize",function (){OnBlindReSize("_WT_BlindVIEWLayer") });
			 }
		}
	}
	if((this.m_CloseTimeUseYN && this.m_CloseTime != "" && this.m_CreateObjectStr != "") ) //자동 닫힘
	{	
		if ( typeof inData == "number")
			this.TimeID = setInterval(this.m_CreateObjectStr + ".OnCloseWait()",inData);
		else
			this.TimeID = setInterval(this.m_CreateObjectStr + ".OnCloseWait()",this.m_CloseTime);
	}
}
function OnBlindReSize(ID)
{
		var IsWTBlindLayer = document.all[ID];
		if (typeof IsWTBlindLayer == "object")
		{
			IsWTBlindLayer.style.width=parseInt(document.documentElement.scrollWidth)  + "px";
			IsWTBlindLayer.style.height=parseInt(document.documentElement.scrollHeight)   + "px";
			
			//IsWTBlindLayer.style.display =  "block";
		}

}
function InitMsgLayer()
{

	var topVal = "5";
	var rtnDivStr	= "";



		rtnDivStr	+= "<div ID='_WT_FLHVIEWLayer_"+this.m_count+"' style='width:0px;height0px;position:absolute;z-index:1001;overflow:hidden;border:0px solid #F19000;left:0;top:0;' >";

	rtnDivStr	+= "<iframe src='"+LocalPath+"loading.htm' id='_WT_MSGVIEWIfr_"+this.m_count+"' name='_WT_MSGVIEWIfr_"+this.m_count+"' !ALLOWTRANSPARENCY='true' style='width:"+ Number(this.m_MsgBoxWidth ) +"px;height:"+  Number(this.m_MsgBoxHeight ) + "px;' topmargin='0' leftmargin='0' marginwidth='0' marginheight='0' frameborder='0' scrolling='no'></iframe>";

	rtnDivStr	+= "</div>";
	return rtnDivStr;
}
function InitBlindLayer()
{
	var rtnDivStr	= "";
	rtnDivStr	+= "<DIV   id='_WT_BlindVIEWLayer' style='width:0px;height:0px;position:absolute;z-index:1000;overflow:hidden;top:0px;left:0px;filter: alpha(opacity="+this.m_BlindAlpha+");background-color:"+this.m_BlindColor+";'  ondragstart='return false' onselectstart='return false' oncontextmenu='javascript:return false'  >";
	rtnDivStr	+= "<table width=100% height=100% cellpadding=0 cellspacing=0 border='0' >";
	rtnDivStr	+= "<tr>";
	rtnDivStr	+= "<td style='text-align:center;'><img src='" +LocalPath+ "loading.gif' width='32' height='32'>";
	rtnDivStr	+= "</td>";
	rtnDivStr	+= "</tr>";
	rtnDivStr	+= "</table>";
	rtnDivStr	+= "</DIV>";
	return rtnDivStr;
}

function WinBodyHTML()
{
	var winmsg = "";
	winmsg +='<title>Waiting　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　</title>';
	winmsg +='<body leftmargin="0" topmargin="0" marginheight="0" marginwidth="0" style="background-color:transparent" oncontextmenu="javascript:return false" ondragstart="javascript:return false" onselectstart="javascript:return false">';
	winmsg +='<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="'+this.m_MsgBoxBackGroundColor+'" style="background-image:url('+this.m_MsgBoxBackImageUrl+');font-size:'+this.m_MsgBoxFontSize+';font-family:'+this.m_MsgBoxFont+';color:'+this.m_MsgBoxFontColor+';">';
	if(this.m_MsgUseYN)//메시지 사용
	{
		winmsg +='<tr height="80%">';
		winmsg +='<td align="center">';
		winmsg +=this.m_Msg;
		winmsg +='</td>';
		winmsg +='</tr>';
	}

	if(this.m_PressUseYN && this.m_PressImageUrl != "")//상태바 사용
	{
		winmsg +='<tr>';
		winmsg +='<td align="center">';
		winmsg +='<img src="'+ this.m_PressImageUrl +'" >';
		winmsg +='</td>';
		winmsg +='</tr>';
	}
	winmsg +='</table>';
	winmsg +='</body>';
	return winmsg;
}
function WT_LayerCenter()
{

	var IsWTLayer = document.all["_WT_FLHVIEWLayer_"+this.m_count+""];
	this.TimeScroll = setInterval("WT_scrollLayer('"+"_WT_FLHVIEWLayer_"+this.m_count+"');", 1);
	//위치 설정
//	IsWTLayer.style.left = ((document.body.clientWidth+document.body.scrollLeft)/2 - document.all['_WT_FLHVIEWLayer_'+this.m_count].offsetWidth/2) +'px';
//	IsWTLayer.style.top = '200px';

}

function  WT_scrollLayer(obj)
{
	 var H_top_point, H_start_point, H_end_point;
	 var W_top_point, W_start_point, W_end_point;

	 var obj_layer   = document.getElementById(obj);

	 H_top_point   = obj_layer.style.top;
	 H_start_point = parseInt(H_top_point, 0);

	 W_top_point   = obj_layer.style.left;
	 W_start_point = parseInt(W_top_point, 0);
	 /*
	 end_point   = document.documentElement.scrollTop+50;
	//end_point   = ((parseInt(document.documentElement.clientHeight) + parseInt(document.documentElement.scrollTop))/2) -parseInt(this.m_MsgBoxHeight);
	 limit_point = ((parseInt(document.documentElement.clientHeight) + parseInt(document.documentElement.scrollTop))/2) -parseInt(this.m_MsgBoxHeight);
	*/
	H_end_point   =  parseInt(parseInt(document.documentElement.scrollTop) +(parseInt(document.documentElement.clientHeight)-parseInt(obj_layer.offsetHeight))/2,0) ;
	H_limit_point = 0;

	W_end_point   =  parseInt(parseInt(document.documentElement.scrollLeft) +(parseInt(document.documentElement.clientWidth)-parseInt(obj_layer.offsetWidth))/2,0) ;
	W_limit_point = 0;

	 if ( H_end_point < H_limit_point )  H_end_point = H_limit_point;
	 if ( H_end_point < H_top_point )    H_end_point = H_top_point;
	 if ( H_start_point != H_end_point )
	 {

		  H_scroll_amount = Math.ceil( Math.abs( H_end_point - H_start_point ) / 5 );
		  obj_layer.style.top = parseInt(H_top_point, 0) + ( ( H_end_point < H_start_point ) ? -H_scroll_amount : H_scroll_amount );
	 }
	 if ( W_end_point < W_limit_point )  W_end_point = W_limit_point;
	 if ( W_end_point < W_top_point )    W_end_point = W_top_point;

	 if ( W_start_point != W_end_point )
	 {

		  W_scroll_amount = Math.ceil( Math.abs( W_end_point - W_start_point ) / 5 );
		  obj_layer.style.left = parseInt(W_top_point, 0) + ( ( W_end_point < W_start_point ) ? -W_scroll_amount : W_scroll_amount );
	 }
}
