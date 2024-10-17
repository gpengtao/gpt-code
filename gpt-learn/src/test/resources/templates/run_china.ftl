<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Centered Table Example</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .table-container {
            width: 90%; /* 表格容器的宽度 */
            margin: 50px auto; /* 上下边距为50px，左右边距自动，实现居中 */
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%; /* 表格宽度为容器宽度的90% */
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

        /* 设置列宽 */
        th:first-child, td:first-child {
            width: 6%;
        }

        th:nth-child(2), td:nth-child(2) {
            width: 30%;
        }

        th:nth-child(3), td:nth-child(3) {
            width: 7%;
        }

        th:nth-child(4), td:nth-child(4) {
            width: 15%;
        }

        th:nth-child(5), td:nth-child(5) {
            width: 17%;
        }

        th:nth-child(6), td:nth-child(6) {
            width: 10%;
        }
    </style>
</head>
<body>
<div class="table-container">
    <h1>Table Example</h1>
    <table>
        <tr>
            <th>开赛时间</th>
            <th>比赛名称</th>
            <th>赛事等级</th>
            <th>比赛地点</th>
            <th>比赛项目</th>
            <th>赛事规模</th>
        </tr>
        <#list races as race>
            <tr>
                <td>${race.raceTime!""}</td>
                <td>${race.raceName!""}</td>
                <td>${race.raceGrade!""}</td>
                <td>${race.raceAddress!""}</td>
                <td>${race.raceItemForShow!""}</td>
                <td>${race.raceScale!""}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>