<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>게시판 목록</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        h2 { margin-bottom: 20px; }
        a.button, button {
            display: inline-block;
            padding: 8px 16px;
            margin: 10px 0;
            background-color: black;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        button.delete { background-color: #e74c3c; }
    </style>
</head>
<body>
<h2> 게시판 목록</h2>

<a href="/board/write" class="button"> 글쓰기</a>

<table>
    <tr>
        <th>ID</th>
        <th>제목</th>
        <th>작성자</th>
        <th>내용</th>
        <th>조회수</th>
        <th>관리</th>
    </tr>
    <c:forEach var="post" items="${posts}">
        <tr>
            <td>${post.id}</td>
            <td><a href="/board/detail?id=${post.id}">${post.title}</a></td>
            <td>${post.author}</td>
            <td>${post.content}</td>
            <td>${post.viewCount}</td>
            <td>
                <button class="delete" onclick="deletePost(${post.id})">삭제</button>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    async function deletePost(id) {
        if (!confirm("정말 삭제하시겠습니까?")) return;

        const res = await fetch(`/api/posts/${id}`, {
            method: "DELETE"
        });

        if (res.ok) {
            alert("삭제 완료!");
            location.reload();
        } else {
            alert("삭제 실패!");
        }
    }
</script>

</body>
</html>
