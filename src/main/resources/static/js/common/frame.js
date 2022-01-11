function getXY(selObj) {

	if ( document.layers ) return selObj;
	var rd = { x:0 ,y:0 ,w:0,h:0};

//	rd.w = rd.offsetWidth;//(typeof selObj.style.width == "string" ? parseInt(selObj.style.width) : parseInt(selObj.width)) ;
//	rd.h = (typeof selObj.style.height == "string" ? parseInt(selObj.style.height) : parseInt(selObj.height)) ;

		rd.w = parseInt( selObj.offsetWidth );
		rd.h = parseInt( selObj.offsetHeight );
		var Scrollx = parseInt(document.documentElement.scrollLeft);
		var Scrolly =  parseInt(document.documentElement.scrollTop) ;
	var i = 0;
	do { 
			if(selObj ==document.body )
			{
				i = 1;
			}
			rd.x += parseInt( selObj.offsetLeft ) - parseInt( selObj.scrollLeft );
			rd.y += parseInt( selObj.offsetTop ) - parseInt( selObj.scrollTop );

		selObj = selObj.offsetParent;


	} while ( selObj   );
	if(i ==0)
	{
		rd.x += Scrollx;
		rd.y += Scrolly;
	}
//	rd.x -= Scrollx;
//	rd.y -= Scrolly;

	return rd;

}



function fillZero(num, zerono)
{
	var snum = new String(num);
	var len = snum.length;

	for (var i = 0; i < zerono - len; i++)
		snum = "0" + snum;

	return snum;
}

function LoadLinkResulst(ContentXML)
{
	var Nodes = $(ContentXML).find("LINKNAME");

			var SelectCountValue = Nodes.length;
			if (SelectCountValue == 0)
			{
				alert("해당 메뉴가 존재하지 않습니다.");
				parent.Tabmenu.waitClose("");
				return;
			}
			var LoadURL = Nodes.text();
			var Opentarget = Nodes.attr("MENUCODE");
			var LINKCODE = Nodes.attr("LINKCODE");
			var VALUENAME = Nodes.attr("VALUENAME");
			var WWIDTH = Nodes.attr("PWIDTH");
			var WHEIGHT = Nodes.attr("PHEIGHT");

			if (LoadURL != "")
			{
				if(Opentarget == "1")
					location.href=LoadURL;
				else if(Opentarget == "2")
					window.open(LoadURL);
				else if(Opentarget == "3")
					OpenWinLayer.OnOpenWindow(LoadURL);
				else if(Opentarget == "4")
				{
					window.open(LoadURL,"fullscreenMain","width="+WWIDTH+", height="+WHEIGHT+",toolbars=0,menubars=0,status=1,locationbars=0,resizable=no");
				}
				else if(Opentarget == "5")
					top.location.href=LoadURL;
				else if(Opentarget == "6")
					parent.Tabmenu.nadd(VALUENAME,LINKCODE,LoadURL);
				else 
					location.href=LoadURL;
					parent.Tabmenu.waitClose("");
			}
	/*var Nodes = ContentXML.selectNodes("//LINKNAME")
	var SelectCountValue = Nodes.length;
	if (SelectCountValue == 0)
	{
		window.status="해당 메뉴가 존재하지 않습니다.";
		return;
	}

	var LoadURL = Nodes.item(0).text;
	var Opentarget = Nodes.item(0).getAttribute("MENUCODE");
	var LINKCODE = Nodes.item(0).getAttribute("LINKCODE");
	var VALUENAME = Nodes.item(0).getAttribute("VALUENAME");
	var WWIDTH = Nodes.item(0).getAttribute("PWIDTH");
	var WHEIGHT = Nodes.item(0).getAttribute("PHEIGHT");


	if (LoadURL != "")
	{
		if(Opentarget == "1")
			location.href=LoadURL;
		else if(Opentarget == "2")
			window.open(LoadURL);
		else if(Opentarget == "3")
			OpenWinLayer.OnOpenWindow(LoadURL);
		else if(Opentarget == "4")
		{
			window.open(LoadURL,"fullscreenMain","width="+WWIDTH+", height="+WHEIGHT+",toolbars=0,menubars=0,status=1,locationbars=0,resizable=no");
		}
		else if(Opentarget == "5")
			top.location.href=LoadURL;
		else if(Opentarget == "6")
			parent.Tabmenu.nadd(VALUENAME,LINKCODE,LoadURL);
		else 
			location.href=LoadURL;
	}*/
}
function LoadLink(idx)
{
	var url = "/_admin/_include/menulink.php?menuIdx=" + idx;
	goGet(url,LoadLinkResulst);

}
function LoadLinkStr(idx,Gstr)
{
		if (Gstr.substr(0,1) == "&")
			quotaStr = "";
		else
			quotaStr = "&";
	
	var url = "/_admin/_include/menulink.php?menuIdx=" + idx + quotaStr +encodeURI( Gstr );

	goGet(url,LoadLinkResulst);
}

function GetLoadLink(idx)
{
	var url = "/_admin/_include/menulink.php?menuIdx=" + idx;
	var ContentXML = XMLHttpSync(url,null,"GET");

	var Nodes = ContentXML.selectNodes("//LINKNAME")

	var SelectCountValue =Nodes.length;
	if (SelectCountValue == 0)
	{
		return "";
	}

	return  Nodes.item(0).text;
 

}

//===============================================================
//trim 객체로.. string.trim(); 으로 사용하면 된다.
//===============================================================
String.prototype.trim = function()
{
	return this.replace(/^\s*(\b.*\b|)\s*$/, "$1");
}

//===============================================================
//	브라이져 중앙에 팝업 위치
//===============================================================
function ShowPopUpWin(str,inWidth,inHeight,name,scltype)
{
	y = screen.availHeight;
	x = screen.availWidth;
	xtop = Number((y - inHeight)/2);
	xleft = Number((x - inWidth)/2);
	//기본값 => 넓이 : 443 높이 : 270 ==> 가장 무난하던 높이와 넓이
//	window.open("/com/PopUpDel.asp",name,"width="+inWidth+",height="+inHeight+",top="+xtop+",left="+xleft+",scrollbars="+ scltype +",toolbars=0,menubars=0,locationbars=0,resizeables=0");
	window.open(str,name,"width="+inWidth+",height="+inHeight+",top="+xtop+",left="+xleft+",scrollbars="+ scltype +",toolbars=0,menubars=0,status=1,locationbars=0,resizeables=0");
}


///////////////////////////////////////////////////////////////////////////
// 허용된 byte만큼 입력도중 실시간으로 string자르기
// <textArea>등에 사용하면 됩니다.
// onKeyup="checkByte(this,제한할byte수,"현재byte정보뿌려줄영역의ID");"
// 마지막 인자는 선택사항입니다.
// ex)  onKeyup="checkByte(this,200,'nowByteShowArea');"
///////////////////////////////////////////////////////////////////////////
function getBytes(sString) {
	var c = 0;
	for (var i=0; i<sString.length; i++) {
		c += parseInt(getByte(sString.charAt(i)));
	}
	return c;
}
function getByte(sChar) {
	var c = 0;
	var u = escape(sChar);
	if (u.length < 4) { // 반각문자 : 기본적인 영문, 숫자, 특수기호
		c++; // + 1byte
	} else {
		var s = parseInt(sChar.charCodeAt(0));
		if (((s >= 65377)&&(s <= 65500))||((s >= 65512)&&(s <= 65518))) // 반각문자 유니코드 10진수 범위 : 한국어, 일본어, 특수문자
			c++; // + 1byte
		else // 전각문자 : 위 조건을 제외한 모든 문자
			c += 2; // + 2byte
	}
	return c;
}
function cutOverText(obj,maxByte,viewAreaID) {
	var sString = obj.value;
	var c = 0;
	for (var i=0; i<sString.length; i++) {
		c += parseInt(getByte(sString.charAt(i)));
		if (c>maxByte) {
			obj.value = sString.substring(0,i);
			break;
		}
	}
	showNowByte(obj.value,viewAreaID);
}
function showNowByte(sString,viewAreaID) {
	var vArea = document.getElementById(viewAreaID);
	if (vArea) vArea.innerHTML = getBytes(sString);
}
function checkByte(obj,maxByte,viewAreaID) {
	var sString = obj.value;
	showNowByte(sString,viewAreaID);
	if (getBytes(sString) > maxByte) {2011-07-25
		alert("최대 "+maxByte+"Bytes(한글 "+(maxByte/2)+"자/영문 "+maxByte+"자)까지만 입력하실 수 있습니다.");
		cutOverText(obj,maxByte,viewAreaID);
	}
}

function getProperty(obj, key, defaultvalue)
{
	if (obj && typeof(obj.getAttribute) != "undefined")
	{
		var retval = $(obj).attr(key);
		/*	//기존 소스 이지만 정상 저장이 되어지지 않아 소스 수정
		if(navigator.userAgent.toLowerCase().indexOf("ie")==-1)
			var retval = $(obj).attr(key);
		else
			var retval = obj.getAttribute(key);
		*/
		if (retval != undefined) return retval;
	}

	return defaultvalue;
}

function setProperty(obj, key, val)
{
	if (typeof(obj.setAttribute) != "undefined")
	{
		if (val == undefined)
			obj.removeAttribute(key);
		else
			obj.setAttribute(key, val);
	}
}

function setImgRpOn(obj , val)
{
	if(val == "Y" && obj.src.indexOf("_off.gif") > -1)
		obj.src = obj.src.replace("_off.gif","_on.gif");
	else if(val == "N" && obj.src.indexOf("_on.gif") > -1)
		obj.src = obj.src.replace("_on.gif","_off.gif");
}

function findLayer(what, doc)
{
	if (!doc) doc = document;
	return doc.getElementById(what);
}


function parameters()
{
	this.paramArray = new Array();
}
parameters.prototype.add = function(addname,addValue)
{
	this.paramArray[this.paramArray.length] = addname + "=" + escape(addValue);
}
parameters.prototype.remove = function(removeidx)
{
	if(removeidx < this.paramArray.length)
	 this.paramArray.splice(removeidx,1);  
}
parameters.prototype.getStr = function()
{
	return this.paramArray.join("&");
}
function XMLHttp(url,params,Method,callFun)
{
	if (!params)
	{
		params = "";
	}

	$.ajax({
		type: Method 
		, url: url
		, data: params
		, success:callFun
	}); 
}
function XMLHttpSync(url,params,Method)
{
	var xhr = null;
	if (window.XMLHttpRequest)   //IE 이외의 파이어폭스, 오페라등의 브라우저에서 XMLHttpRequest 객체구하기
	    xhr = new XMLHttpRequest();
	else if(window.ActiveXObject)           // IE에서 XMLHttpRequest 객체 구하기
	 xhr = new ActiveXObject("Microsoft.XMLHTTP");

    xhr.open(Method, url,false);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
     xhr.send(params);
	 return xhr.responseXML;

}
function goGet(url,fun,isWait)
{
	if(fun == undefined || fun == null || fun == "")
		fun = defaultFormResult;

	XMLHttp(url,null,"GET",fun);
	try{
		if(isWait == undefined || isWait)
		parent.Tabmenu.waitOpen("");
		parent.frameResize();
	}catch(e){};
}
function doFile(url,form)
{
	var n = 'f' + Math.floor(Math.random() * 99999);
	var d = document.createElement('DIV');
	d.innerHTML = '<iframe style="display:none" src="about:blank" id="'+n+'" name="'+n+'"></iframe>';
	document.body.appendChild(d);
	form.target= n;
	form.submit();
}
function doPost(url,pdata,fun,isWait)
{
	if(fun == undefined || fun == null || fun == "")
		fun = defaultFormResult;
	XMLHttp(url,pdata,"POST",fun);
	try{
		if(isWait != undefined || isWait)
			parent.Tabmenu.waitOpen("");
	}catch(e){};
}

function getValidListStr(f)
{
	var IsValue = new Array();
	for(var i =0; i< f.length;i++)
	{
		var tName = f[i].tagName;
		if(tName == "CHECKBOX" || tName == "RADIO" || tName == "INPUT" || tName == "SELECT" || tName == "TEXTAREA")
		{
			if(getProperty(f[i],"type","") == "checkbox" || getProperty(f[i],"type","") == "radio" )
			{
			if(f[i].checked)
				IsValue[IsValue.length] = getProperty(f[i],"name","") + "=" + getProperty(f[i],"value","");//escape(getProperty(f[i],"value","")) ;
			}else if(tName == "SELECT"  || tName == "TEXTAREA")
				IsValue[IsValue.length] = getProperty(f[i],"name","") + "=" + encodeURIComponent(f[i].value);//escape(f[i].value) ;
			else {
				//alert($(f[i]).attr('name') + ' : ' + $(f[i]).attr('value'));
				IsValue[IsValue.length] = getProperty(f[i],"name","") + "=" + getProperty(f[i],"value","").replace('&', '%26');//escape(getProperty(f[i],"value","")) ;
				
			}

		}
	}
//	return encodeURIComponent(IsValue.join("&"));
	return IsValue.join("&");
}

function doForm(f,isV)
{
	if(isV == undefined || isV)
	{
		if(!IsValidList(f))
		{
			return false;
		}
	}
	
	var CallB = "";
		CallB = getProperty(f,"callback","defaultFormResult");
	var actionUrl = f.action;
	f.target="";

	if(f.enctype == "multipart/form-data")
		doFile(actionUrl,f)
	else if(f.method == "post")
		doPost(actionUrl,getValidListStr(f),eval(CallB))
	else
		doGet(actionUrl+"?"+getValidListStr(f),eval(CallB))
	return false;
}
function defaultFormResultFile(id)
{
	var i = document.getElementById(id);
	if (i.contentDocument) {
		var d = i.contentDocument;
	} else if (i.contentWindow) {
		var d = i.contentWindow.document;
	} else {
		var d = window.frames[id].document;
	}
	if (d.location.href == "about:blank") {
		return;
	}	
	defaultFormResult(loadXML(d.body.innerText));
	i.parentNode.removeChild(i);
	//i.removeNode(true);

}
function loadXML(xml)
{
	xml = xml.replace(/\n- /gi, "");
// code for IE
if (window.ActiveXObject)
  {
  xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
  xmlDoc.async=false;
  xmlDoc.loadXML(xml);
	return xmlDoc;
  }
// code for Mozilla, Firefox, Opera, etc.
else 
  {
    var parser = new DOMParser();
    return parser.parseFromString(xml,"text/xml");
  }
}

function defaultFormResult(xmlHttp)
{
	var Nodes = $(xmlHttp).find("row");
	var SelectCountValue = Nodes.length;
	if (SelectCountValue == 0)
	{
		window.status="서버전송을 실패하였습니다.";
		return;
	}
	var gourl = Nodes.text();
	var Bool = Nodes.attr("value");
	var rtnvalue = Nodes.attr("resultmsg");
	if(!eval(Bool))
	{
		alert(rtnvalue);
		return;
	}
	if(rtnvalue != "")
		alert(rtnvalue);
	if(gourl != "")
	{
		if(gourl.indexOf("javascript:") > -1)
			eval(gourl);
		else
			location.href=gourl;
	}
	
	
	
	
	
	/*
	var Nodes = xmlHttp.selectNodes("//row");
	var SelectCountValue =Nodes.length;

	if (SelectCountValue == 0)
	{
		window.status="서버전송을 실패하였습니다.";
		return;
	}

	var gourl = Nodes.item(0).text;
	var Bool = Nodes.item(0).getAttribute("value");
	var rtnvalue = Nodes.item(0).getAttribute("resultmsg");
	if(!eval(Bool))
	{
		alert(rtnvalue);
		return;
	}
	if(rtnvalue != "")
		alert(rtnvalue);
	if(gourl != "")
	{
		if(gourl.indexOf("javascript:") > -1)
			eval(gourl);
		else
			location.href=gourl;
	}
	*/
}

function Popupload(){
  var innerBody = document.body;
  var innerHeight = innerBody.scrollHeight;
  resizeTo(200,innerHeight);
  var innerWidth = innerBody.scrollWidth+(innerBody.offsetWidth - innerBody.clientWidth);
  innerHeight = (innerBody.scrollHeight) * 2 - innerBody.offsetHeight;
  resizeTo(innerWidth,innerHeight);
 }