fetch('http://localhost:8082/users/u')
    .then(response => response.json())
    .then(data => {
        console.log(data)
        let table = ""
        let role = data.roles[0].rolename

        table += ('<tr id="list">')
        table += ('<td>' + data.id + '</td>')
        table += ('<td>' + data.currentfirstname+ '</td>')
        table += ('<td>' + data.currentlastname + '</td>')
        table += ('<td>' + data.age + '</td>')
        table += ('<td>' + data.username + '</td>')
        table += ('<td>' + role)

        table += ('</tr>')

        console.log(table)
        $('#user_table').append(table)
    })