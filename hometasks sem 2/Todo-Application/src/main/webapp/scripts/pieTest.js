function getUsersData(userId, context) {
    $.ajax({
        url:  `${context}/user-statistics?userId=${userId}`,
        type: "GET",
        dataType: "json",
        success: [
            function (response) {
                console.group();
                console.log("response from /user-staistics");
                console.log(response);
                console.groupEnd();
                pieChartInit(response.doneTodos, response.needTodo, userId)
            }
        ]
    });
}

function pieChartInit(value1, value2, userId) {
    console.log('chart init for userId: ', userId)

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