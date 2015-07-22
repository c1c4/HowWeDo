<jsp:useBean id="generoDTO" class="dto.DtoGenero"></jsp:useBean>
<jsp:setProperty property="*" name="generoDTO" />
<link rel=stylesheet type="text/css" href="/HowWeDo/Content/estilos/Mylayout.css" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

<%
	String[] erros = (String[]) request.getAttribute("erros");
	if (erros != null && erros.length > 0) {
%>
<div class="htmldbNotification">
<ul>
	<%
		for (int i = 0; i < erros.length; i++) {
	%>
	<li><%=erros[i]%> <%
 	}
 %>
</ul>
</div>
<%
	}
%>

<table id="obTable" cellspacing="0" cellpadding="0" border="0" style="margin: 5px; width: 1325px;">
	<tbody>
		<tr>
			<td id="dbaseFrameHolder" class="dbaseFrameHolder" width="100%"
				valign="top">
				<table id="obRightHeader" cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<td id="ob_ObjectsDetail" width="100%">Registro de Genêros</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td>

				<form action="/HowWeDo/IncluirGenero" method="post">
				<input type="hidden" name="acao" value="Inclusao">
					<table class="htmldbStandard3" cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td class="htmldbFD">Genero:</td>
							<td><input type="text" name="genero"
								value='<jsp:getProperty property="nome" name="generoDTO"/>'
								size=50 maxlength="50"></td>
						</tr>
						<tr>
							<td><input class="htmldbButton" type="submit" value="Gravar"></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</tbody>
</table>

</BODY>
</HTML>