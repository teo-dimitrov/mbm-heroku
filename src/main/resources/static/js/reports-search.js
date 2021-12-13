const reportsList = document.getElementById('reportsList')
const searchBar = document.getElementById('searchInput')

const allReports = [];

fetch("http://localhost:8080/reports/api").then(response => response.json()).then(data => {
    for (let report of data) {
        allReports.push(report);
    }
})

searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();
    let filteredReports = allReports.filter(report => {
        return report.name.toLowerCase().includes(searchingCharacters);
    });
    displayReports(filteredReports);
})

const displayReports = (reports) => {
    reportsList.innerHTML = reports
        .map((r) => {
            return `<table class="table table-striped table-dark rounded">
        <thead>
       
        </thead>
        <tbody>
        <tr th:each="r : ${reports}" th:object="${r}">
            <th scope="row">${r.id}</th>
            <td><a class="btn text-warning" href="${r.id}/report-details">${r.name}</a></td>
            <td>${r.reportTypeEnum}</td>
            <td>
                <div  class="row justify-content-md-center">
                    <p class="${r.reportStatusEnum.valueAsString === 'UNCHECKED'} ? 'text-danger' : 'text-success'" >${r.reportStatusEnum}</p>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
`
        })
        .join('');
}