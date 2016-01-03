/*公用函数*/
function controllerCenter(type,cmd,params){
      if(navigator.userAgent.toLowerCase().indexOf('iphone')>0){
          document.location="http://QM_APP_WEBVIEW_ENGINE:"+cmd+":"+params;
      }else if(navigator.userAgent.indexOf('Android')>0){
          eval('javascript:QM_APP_WEBVIEW_ENGINE_'+type+'.'+cmd+'("'+params+'")');
      }else{
          alert("Can't use this feature!");
      }
  }

/*调用摄像头及相册部分*/
var $camera={
	      "invokeCamera":function(params){
	          controllerCenter('camera','invoke_camera',params);
	      },
	      "requestAlbum":function(params){
	          controllerCenter('camera','request_albums',params);
	      },
	      "requestAlbumMulti":function(params){
	          controllerCenter('camera','request_albums_multi',params);
	      },
	      "requestAlbumMultiUpload":function(params){
	          controllerCenter('camera','request_albums_multi_upload',params);
	      }
	  }
