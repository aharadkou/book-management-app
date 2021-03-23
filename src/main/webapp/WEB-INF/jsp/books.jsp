<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.epam.training.constants.ConstantsJSP"%>
<!DOCTYPE html>
<head>
    <title>Books</title>
    <jsp:include page="${ConstantsJSP.JSP_HEADER}"/>
</head>
<body>
<ul id="error">
    <c:forEach items="${errorList}" var="error">
        <li>
            ${error}
        </li>
    </c:forEach>
</ul>

<h2 class="text-center">
    Book list
</h2>
<table>
    <thead>
    <tr>
        <td>
            Title
        </td>
        <td>
            Author
        </td>
        <td>
            Description
        </td>
        <td>
            Date
        </td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listBooks}" var="book">
        <tr>
            <td>
                <input type="text" value="${book.title}" form="updateForm${book.id}"
                       name="${ConstantsJSP.PARAM_TITLE}"/>

            </td>
            <td>
                <input type="text" value="${book.author}" form="updateForm${book.id}"
                       name="${ConstantsJSP.PARAM_AUTHOR}"/>
            </td>
            <td>
                <input type="text" value="${book.description}" form="updateForm${book.id}"
                       name="${ConstantsJSP.PARAM_DESCRIPTION}"/>
            </td>
            <td>
                <input type="date" value="${book.releaseDate}" form="updateForm${book.id}"
                       name="${ConstantsJSP.PARAM_DATE}"/>
            </td>
            <td>
                <form id="updateForm${book.id}" method="POST"
                      action="${pageContext.request.contextPath}${ConstantsJSP.PATH_BOOK_UPDATE}">
                    <input name="${ConstantsJSP.PARAM_ID}" type="hidden" value="${book.id}"/>
                    <input type="submit" value="Update" />
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h2>Add new book</h2>
<form method="POST" action="${pageContext.request.contextPath}${ConstantsJSP.PATH_BOOK_ADD}">
    <input type="text" name="${ConstantsJSP.PARAM_TITLE}"/>
    <input type="text" name="${ConstantsJSP.PARAM_AUTHOR}"/>
    <input type="text" name="${ConstantsJSP.PARAM_DESCRIPTION}"/>
    <input type="date" name="${ConstantsJSP.PARAM_DATE}"/>
    <input type="submit" value="Add"/>
</form>
</body>
</html>
