<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.sampletaw.entity.CuentaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.sampletaw.entity.MovimientoEntity" %>
<%@ page import="es.taw.sampletaw.entity.TipocomisionEntity" %><%--
  Created by IntelliJ IDEA.
  User: sergio
  Date: 29/05/2023
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    List<CuentaEntity> listaCuentas = (List<CuentaEntity>) request.getAttribute("listaCuentas");
    List<TipocomisionEntity> tipoComision = (List<TipocomisionEntity>) request.getAttribute("tipoComision");

%>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <%--@elvariable id="Filtro" type=""--%>
    <form:form action="/filtrar" method="post" modelAttribute="Filtro">
        <td>Comisión:<form:select path="tipoComision" items="${tipoComision}" itemLabel="comision"></form:select></td>
        <td>Saldo > <form:input path="saldo"></form:input>
            <form:button>Filtrar</form:button></td>
    </form:form>
</table>

<table border="2">
    <tr>
        <th>IBAN</th>
        <th>SALDO ACTUAL</th>
        <th>MOVIMIENTOS</th>
        <th>COMISION APLICADA</th>
    </tr>
    <%
        for (CuentaEntity cuenta : listaCuentas) {
            List<MovimientoEntity> movimientos = cuenta.getMovimientosByCuentaid();
    %>
    <tr>
        <td><a href="/cuenta?id=<%=cuenta.getCuentaid()%>"><%=cuenta.getIban()%>
        </a>
        </td>
        <td><%=cuenta.getSaldoactual()%>
        </td>
        <td>
            <%
                for (MovimientoEntity movimiento : movimientos) {
            %>
            <%=movimiento.getFecha() + " " + movimiento.getDescripcion() + " " + movimiento.getCantidad()%><br>
            <%
                }%>
        </td>
        <td><%=cuenta.getTipocomisionByComision().getComision()%>
        </td>
    </tr>

    <%

        }%>
</table>
</body>
</html>
