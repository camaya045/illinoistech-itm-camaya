<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Echo Response</title>
</head>
<body>

<h2>Hello, <strong>${name}</strong>! You are <strong>${age}</strong> years old.</h2>


<h3>Request Details:</h3>
<ul>
    <li>
        <strong>Current server time:</strong> ${currentTime}
    </li>

    <li>
        <strong>Server info:</strong> ${serverInfo}
    </li>

    <li>
        <strong>Request method:</strong> ${requestMethod}
    </li>
</ul>

</body>
</html>
