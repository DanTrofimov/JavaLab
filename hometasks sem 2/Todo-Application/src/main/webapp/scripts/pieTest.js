function getUsersData(userId, context) {
    $.ajax({
        url:  `${context}/user-statistics?userId=${userId}`,
        type: "GET",
        dataType: "json",
        success: [
            function (response) {
                console.log(response.doneTodo);
                console.log(response.needTodo)
                pieChartInit(response.doneTodo, response.needTodo, userId)
            }
        ]
    });
}

function pieChartInit(value1, value2, userId) {
    console.log('chart init', userId)

    let element = document.getElementById(userId + "-myPieChart")
    new Chart(element.getContext('2d'), {
        type: 'pie',

        data: {
            labels: ['done', 'need to do'],
            datasets: [{
                label: '',
                backgroundColor: ['#b5deb8', '#f0a8b7'],
                data: [value1, value2]
            }]
        },

        options: {}
    })
}