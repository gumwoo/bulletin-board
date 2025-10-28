<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 작성</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        form { width: 500px; margin: 0 auto; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input[type="text"], textarea {
            width: 100%; padding: 8px; margin-top: 5px;
            border: 1px solid #ccc; border-radius: 4px;
        }
        textarea { height: 150px; resize: none; }
        button {
            margin-top: 15px; padding: 10px 20px;
            background-color: black; color: white;
            border: none; border-radius: 4px; cursor: pointer;
        }
        button:hover { background-color: #45a049; }
        a { text-decoration: none; color: black; display: inline-block; margin-top: 10px; }
    </style>
</head>
<body>
<h2> 게시글 작성</h2>
<form id="postForm">
    <label>제목</label>
    <input type="text" name="title" required />

    <label>작성자</label>
    <input type="text" name="author" required />

    <label>내용</label>
    <textarea name="content" required></textarea>

    <button type="submit">등록하기</button>
</form>
<a href="/board">← 목록으로 돌아가기</a>

<script>
    document.getElementById("postForm").addEventListener("submit", async (e) => {
        e.preventDefault();

        const data = {
            title: e.target.title.value,
            author: e.target.author.value,
            content: e.target.content.value
        };

        const res = await fetch("/api/posts", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (res.ok) {
            alert("등록 완료!");
            window.location.href = "/board";
        } else {
            alert("등록 실패!");
        }
    });
</script>
</body>
</html>
