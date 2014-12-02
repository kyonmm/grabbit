<div class="col-md-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h1>Dash Board</h1>
        </div>

        <div class="panel-body">
            This page should shows various test summaries graph.
        </div>
    </div>
</div>
<gvisualization:apiImport/>
<gvisualization:lineCoreChart elementId="TestCaseChangesGroupByTagTimeChart"
                              legend="none"
                              curveType="function"
                              columns="${TestCaseChangesGroupByTagTimeChart}"
                              data="${TestCaseChangesGroupByTagTimeChartData}" />
<gvisualization:lineCoreChart elementId="TestCaseTotalTimeChart"
                              legend="none"
                              curveType="function"
                              columns="${TestCaseTotalTimeChart}"
                              data="${TestCaseTotalTimeChartData}" />
<gvisualization:pieCoreChart
        elementId="TestCaseCountGroupByTag"
        title=""
        legend="none"
        columns="${TestCaseCountGroupByTag}"
        data="${TestCaseCountGroupByTagData}" />
<div class="col-md-12">
    <div class="panel panel-default">
    <div class="panel-heading">タグ別テストケース数の変更数</div>
    <div id="TestCaseChangesGroupByTagTimeChart"></div>
    </div>
</div>
<div class="col-md-4">
    <div class="panel panel-default">
    <div class="panel-heading">タグの割当率</div>
    <div id="TestCaseCountGroupByTag"></div>
    </div>
</div>
<div class="col-md-8">
    <div class="panel panel-default">
    <div class="panel-heading">テストケース総数</div>
    <div id="TestCaseTotalTimeChart"></div>
    </div>
</div>