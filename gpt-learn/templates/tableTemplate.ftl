<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Table Example</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>Table Example</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Age</th>
        <th>City</th>
    </tr>
    <#list races as race>
        <tr>
            <td>${race.raceTime}</td>
            <td>${race.raceName}</td>
            <td>${race.raceGrade}</td>
            <td>${race.raceAddress}</td>
        </tr>
    </#list>
</table>
</body>
</html>