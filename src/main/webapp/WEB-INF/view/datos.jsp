<%@ page import="es.taw.sampletaw.entity.CuentaEntity" %>
<%@ page import="es.taw.sampletaw.entity.CuentaclienteEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sergio
  Date: 29/05/2023
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    CuentaEntity cuenta = (CuentaEntity) request.getAttribute("cuenta");
    List<CuentaclienteEntity> clientes = cuenta.getCuentaclientesByCuentaid();
%>
<head>
    <title>Title</title>
</head>
<body>
<table border="2">
    <tr>
        <th>IBAN</th>
        <td><%=cuenta.getIban()%>
        </td>
    </tr>
    <tr>
        <th>FECHA CREACION</th>
        <td><%=cuenta.getFechacreacion()%>
        </td>
    </tr>
    <tr>
        <th>SALDO</th>
        <td><%=cuenta.getSaldoactual()%>
        </td>
    </tr>
    <tr>
        <th>CLIENTES</th>
        <td>
            <% for (CuentaclienteEntity cliente : clientes) {
            %>
            <%=cliente.getClienteByClienteid().getNombrecompleto()%>
            <a href="/cambiar?id=<%=cuenta.getCuentaid()%>&cliente=<%=cliente.getClienteByClienteid().getClienteid()%>"><%="(" + cliente.getRolclienteByRolid().getDescripcion() + ")" %>
            </a>
            <a href="/borrar?id=<%=cuenta.getCuentaid()%>&cliente=<%=cliente.getCuentaclienteid()%>"> X
            </a>

            <br>

            <%
                }%>
        </td>
    </tr>


</table>
<a href="/">Volver al listado</a>
</body>
</html>
