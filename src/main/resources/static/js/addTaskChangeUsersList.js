function loadUsers(projectId) {
    var url = "/tasks/usersByProjectId/" + projectId;
    $.get(url, function(data) {
        var select = $('select[name="assignedToUser"]');
        select.find('option').remove();
        select.append('<option value="0">User</option>');
        $.each(data, function(index, user) {
            select.append('<option value="' + user.id + '">' + user.login + '</option>');
        });
    });
}