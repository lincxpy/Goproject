
{{define "addadit"}}
<!DOCTYPE html>
<!-- saved from url=(0052)http://getbootstrap.com/docs/4.0/examples/dashboard/ -->
<html lang="en">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>Dashboard Template for Bootstrap</title>
		<!-- Bootstrap core CSS -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
		<style type="text/css">
			/* Chart.js */
			
			@-webkit-keyframes chartjs-render-animation {
				from {
					opacity: 0.99
				}
				to {
					opacity: 1
				}
			}
			
			@keyframes chartjs-render-animation {
				from {
					opacity: 0.99
				}
				to {
					opacity: 1
				}
			}
			
			.chartjs-render-monitor {
				-webkit-animation: chartjs-render-animation 0.001s;
				animation: chartjs-render-animation 0.001s;
			}
		</style>
	</head>

	<body>
		{{template "content1"}}
		<div class="container-fluid">
			<div class="row">
				<!--引入侧边栏-->
				{{template "content2"}}
			
				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
					<div class="container">
					<h2>Blog</h2>
					<form id="form111">
						<div class="form-group">
							<label class="label-control">Tag</label>
							<input name="tag" value="{{.Tag}}" type="text" class="form-control input-sm" placeholder="golang">
						</div>
						<div class="form-group">
							<label>Categorie</label>
							<input name="categorie" value="{{.Categorie}}" type="text" class="form-control fc-clear" placeholder="后端语言" >
						</div>
						<div class="form-group">
							<label>Title</label>
							<input name="title" value="{{.Title}}" class="form-control fc-clear" placeholder="golang学习">
						</div>
						<div class="form-group">
							<label>Context</label>
							<textarea name="context" rows="10" class="form-control fc-clear" >{{.Context}}
							</textarea>
							<!-- <input value="{{.Context}}" name="context" type="text" class="form-control" placeholder="人生苦短，js搞死人"> -->
						</div>
						<div class="form-group">
							<label>Author</label>
							<input name="author" value="{{.Author}}" class="form-control fc-clear" placeholder="菜鸟">
						</div>
						<div class="form-group">
							<label>Date</label>
							{{if .Date}}
							<input value="{{.Date}}" name="date" type="text" class="form-control">
							{{else}}
							<input id="today" name="date" type="text" class="form-control">
							{{end}}
						</div>
						
					</form>
					{{if .}}
					<input  name="id" type="hidden" class="form-control1" value="{{.ID}}">
					<button id="/blogadit" class="btn btn-primary" onclick="doSend1(this)">修改</button>
					{{else}}
					<input  name="id" type="hidden" class="form-control1" value="0">
					<button id="/blogadd" class="btn btn-primary" onclick="doSend1(this)">添加</button>
					{{end}}
				</div>
				</main>
				</div>
			</div>
		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script type="text/javascript" src="statics/js/jquery-3.2.1.slim.min.js"></script>
		<script type="text/javascript" src="statics/js/popper.min.js" ></script>
		<script type="text/javascript" src="statics/js/bootstrap.min.js" ></script>
		<!-- Icons -->
		<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
		<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
		<script type="text/javascript" src="statics/js/feather.min.js" ></script>
		<script type="text/javascript">
			function today(){//构建方法
					var today=new Date();//new 出当前时间
					var h=today.getFullYear();//获取年
					var m=today.getMonth()+1< 10 ? "0" + (today.getMonth() + 1) : today.getMonth() + 1;;//获取月
					var d=today.getDate() < 10 ? "0" + today.getDate() : today.getDate();//获取日
					var H = today.getHours()< 10 ? "0" + today.getHours() : today.getHours();//获取时
					var M = today.getMinutes()< 10 ? "0" + today.getMinutes() : today.getMinutes();//获取分
					var S=today.getSeconds()< 10 ? "0" + today.getSeconds() : today.getSeconds();
					return h+"-"+m+"-"+d+" "+H+":"+M+":"+S; //返回 年-月-日 时:分:秒
			}
			document.getElementById("today").value = today();//将获取到的 年-月-日 时:分:秒 赋给input文本输入框
			function transformToJson(formData){
				var obj={}
				for (var i in formData) {
					obj[formData[i].name]=formData[i]['value'];
				}
				return obj;
			}
			function doSend1(e){
				console.log("send")
				 var js1= $("#form111").serializeArray();
				 var data1 = JSON.stringify(transformToJson(js1));
				 var url=e.id
				 console.log(data1)
				axios({
					headers: {
						'Content-Type': 'application/json'
					},
				method: 'post',
				url: url,
				data: data1
				})
				.then(function (res){
					alert(res.data.msg);
					window.location.href="/bloglist";
				})
				.catch(function (err){
					console.log("post err",err);
					alert(err);
					window.location.reload();
				})
			}
						    function signOut(){
        var ck = getCookie("Authorization");
        console.log(ck);
        if(ck!=""){
            document.cookie="Authorization=";
        }
        window.location.href="/login";
    }
    function getCookie(name){
    var strcookie = document.cookie;//获取cookie字符串
    var arrcookie = strcookie.split("; ");//分割
    //遍历匹配
    for ( var i = 0; i < arrcookie.length; i++) {
        var arr = arrcookie[i].split("=");
        if (arr[0] == name){
            return arr[1];
        }
    }
    return "";
}
    </script>
	</body>
</html>
{{end}}