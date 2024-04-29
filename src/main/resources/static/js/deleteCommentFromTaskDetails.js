function deleteComment(button) {
    var commentId = button.getAttribute("data-commentId");
    fetch("/comments/delete/" + commentId, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (response) {
        if (response.ok) {
            window.location.reload();
        }
    }).catch(function (error) {
        console.error('Error:', error);
    });
}