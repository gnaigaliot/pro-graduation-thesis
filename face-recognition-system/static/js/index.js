$("#timekeeping-tab").click(function () {
    $("#dateNow").show();
});
$("#hrm-tab").click(function () {
    $("#dateNow").hide();
});
$("#history-tab").click(function () {
    $("#dateNow").hide();
});
var TAG = {
    item_image: (data) => {
        return `<li class="nav-item list-user">
                    <img class="card-img-top"src="${data.url}" alt="Card image cap">
                    <div>
                        <p class="card-text">${data.empName}</p>
                        <p class="card-text">${data.checkInTime}</p>
                    </div>
                </li>`
    }
};
var TAG_SUM_EMP = {
    sum_emp: (data) => {
        return `<p>Tổng số nhân viên : ${data.totalEmp} </p>
                <p>Tổng số nhân viên lạ: ${data.totalEmpStrange}</p>`
    }
};

function refreshTime() {
    var dateString = new Date().toLocaleString("en-US");
    var formattedString = dateString.replace(", ", " - ");
    $("#dateNow").text(formattedString)
}

setInterval(refreshTime, 1000);