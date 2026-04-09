<!DOCTYPE html>
<html>
<head>
    <title>Input Form</title>
</head>
<body>
    <h2>User Information</h2>
    <form action="process" method="post">
        <div>
            Name: <input type="text" name="name" required>
        </div>

        <div>
            Favorite Food: <input type="text" name="food" required>
        </div>

        <div>
            Age: <input type="number" name="age" required>
        </div>

        <br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>