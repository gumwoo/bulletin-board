<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 상세보기</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { width: 600px; margin: 0 auto; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input, textarea { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
        textarea { height: 150px; resize: none; }
        button { margin-top: 15px; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; }
        .save { background-color: black; color: white; }
        .delete { background-color: #e74c3c; color: white; margin-left: 10px; }
    </style>
</head>
<body>
<div class="container">
    <h2> 게시글 상세보기</h2>
    <input type="hidden" id="postId" value="${param.id}" />
    <label>제목</label>
    <input type="text" id="title" />

    <label>작성자</label>
    <input type="text" id="author" readonly />

    <label>내용</label>
    <textarea id="content"></textarea>

    <button class="save" onclick="updatePost()">수정</button>
    <a href="/board" class="delete">목록으로</a>
</div>

<script>
    const id = document.getElementById("postId").value;

    // 페이지 로드 시 게시글 불러오기
    window.onload = async () => {
        const res = await fetch(`/api/posts/${id}`);
        const data = await res.json();
        document.getElementById("title").value = data.title;
        document.getElementById("author").value = data.author;
        document.getElementById("content").value = data.content;
    };

    // 게시글 수정
    async function updatePost() {
        const updated = {
            title: document.getElementById("title").value,
            content: document.getElementById("content").value,
            author: document.getElementById("author").value
        };

        const res = await fetch(`/api/posts/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(updated)
        });

        if (res.ok) {
            alert("수정 완료!");
            location.href = "/board";
        } else {
            alert("수정 실패!");
        }
    }
</script>
</body>
</html>
