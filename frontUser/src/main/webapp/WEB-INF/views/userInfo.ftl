<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>P2P平台-用户信息</title>
		<#include "common/links-tpl.ftl" />
		<#include "common/loadSystemDictionary-macro.ftl" />
		<link type="text/css" rel="stylesheet" href="/css/account.css" />
		<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
		<script>
			$(function(){
			//提交表单
			$("#submitBtn").click(function(){
				$("#userInfoForm").ajaxSubmit({
					success: function(data){
						if(data.success){
							$.messager.confirm("提示","资料保存成功",function(){
								window.location.reload();
							});
						}else{
							$.messager.alert(data.msg);
						}
					}
				});
				 	return false;
			});
			});
		</script>		
	</head>
	<body>
		<!-- 网页顶部导航 -->
		<#include "common/head-tpl.ftl" />
		
		<!-- 网页导航 -->
		<#assign currentNav="personal"/>
		<#include "common/navbar-tpl.ftl" />
		
		<div class="container">
			<div class="row">
				<!--导航菜单-->
				<div class="col-sm-3">
					<#assign currentMenu="userInfo" />
					<#include "common/leftmenu-tpl.ftl" />
				</div>
				<!-- 功能页面 -->
				<div class="col-sm-9">
					<div class="panel panel-default">
						<div class="panel-heading">
							个人资料
						</div>
						<form id="userInfoForm" class="form-horizontal" action="/basicInfo_save.do" method="post" style="width: 700px;">
							<div class="form-group">
								<label class="col-sm-4 control-label">
									用户名
								</label>
								<div class="col-sm-8">
									<p class="form-control-static">${loginInfo.username}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label">
									真实姓名
								</label>
								<div class="col-sm-8">
									<p class="form-control-static">
										<#if (userInfo.isRealAuth)>
											${userInfo.realName!''}
										<#else>
											未认证
											<a href="/realAuth.do">[马上认证]</a>
										</#if>
									</p>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-4 control-label">
									身份证号码
								</label>
								<div class="col-sm-8">
									<p class="form-control-static">	
										<#if (userInfo.isRealAuth)>
											${userInfo.idNumber!''}
										<#else>
											未认证
											<a href="/realAuth.do">[马上认证]</a>
										</#if>
									</p>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-4 control-label">
									手机号码
								</label>
								<div class="col-sm-8">
									<label style="width: 250px;" class="form-control">${(userInfo.phoneNumber)!''}</label>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-4 control-label">
									个人学历
								</label>
								<div class="col-sm-8">
									<select class="form-control" id="educationBackground" name="educationBackground.id" style="width: 180px" autocomplate="off">
										<option value="">请选择</option>
										<@loadDictionary sn="educationBackground" />
									</select>
									<script type="text/javascript">
										$("#educationBackground option[value=${(userInfo.educationBackground.id)!-1}]").attr("selected",true);
									</script>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-4 control-label">
									月收入
								</label>
								<div class="col-sm-8">
									<select class="form-control" id="incomeGrade" name="incomeGrade.id" style="width: 180px" autocomplate="off" >
										<option value="">请选择</option>
										<@loadDictionary sn="incomeGrade" />
									</select>
									<script type="text/javascript">
										$("#incomeGrade option[value=${(userInfo.incomeGrade.id)!-1}]").attr("selected",true);
									</script>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-4 control-label">
									婚姻情况
								</label>
								<div class="col-sm-8">
									<select class="form-control" id="marriage" name="marriage.id" style="width: 180px" autocomplate="off">
										<option value="">请选择</option>
										<@loadDictionary sn="marriage" />
									</select>
									<script type="text/javascript">
										$("#marriage option[value=${(userInfo.marriage.id)!-1}]").attr("selected",true);
									</script>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label">
									子女情况
								</label>
								<div class="col-sm-8">
									<select class="form-control" id="kidCount" name="kidCount.id" style="width: 180px" autocomplate="off">
										<option value="">请选择</option>
										<@loadDictionary sn="kidCount" />
									</select>
									<script type="text/javascript">
										$("#kidCount option[value=${(userInfo.kidCount.id)!-1}]").attr("selected",true);
									</script>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label">
									住房条件
								</label>
								<div class="col-sm-8">
									<select class="form-control" id="houseCondition" name="houseCondition.id" style="width: 180px" autocomplate="off">
										<option value="">请选择</option>
										<@loadDictionary sn="houseCondition" />
									</select>
									<script type="text/javascript">
										$("#houseCondition option[value=${(userInfo.houseCondition.id)!-1}]").attr("selected",true);
									</script>
								</div>
							</div>
							
							<div class="form-group">
								<button id="submitBtn" type="submit" class="btn btn-primary col-sm-offset-5" data-loading-text="数据保存中" autocomplate="off">
									保存数据
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>		
		
		<#include "common/footer-tpl.ftl" />
	</body>
</html>