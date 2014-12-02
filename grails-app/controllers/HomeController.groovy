import grails.plugin.springsecurity.annotation.Secured
import org.grails.plugins.google.visualization.util.DateUtil
import org.kyonmm.grabbit.Tag
import org.kyonmm.grabbit.TestCase

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class HomeController {

    def date = new Date()
    def index() {
        def testCases = TestCase.findAll{lastUpdated > date - 10}.sort{it.lastUpdated}
        def groupByUpdateDate = testCases.groupBy {it.lastUpdated.format("YYYYMMdd")}
        def tags = testCases.tags.flatten().unique()
        def groupByCreateDate = testCases.groupBy {it.dateCreated.format("YYYYMMdd")}

        List<Date> targetDates = []
        10.downto(0){
            targetDates << date - it
        }

        def TestCaseGroupByUpdateDate = targetDates.collect{
            def currentDateTestCases = groupByUpdateDate[it.format("YYYYMMdd")]
            def r = [it]
            currentDateTestCases ?
                    r.addAll(tags.collect{tag -> currentDateTestCases.count{it.tags.contains(tag)}})
                    : r.addAll(tags.collect{"0"})
            r
        }
        def TestCaseGroupByCreateDate = targetDates.collect{
            def currentDateTestCases = groupByCreateDate[it.format("YYYYMMdd")]
            def r = [it]
            currentDateTestCases ?
                    (r += currentDateTestCases.size())
                    : (r += 0)
            r
        }

        def dashboardModel = [
                TestCaseChangesGroupByTagTimeChart:[['date', 'Date']] + tags.collect{["number", it.name]},
                TestCaseChangesGroupByTagTimeChartData:TestCaseGroupByUpdateDate,
                TestCaseCountGroupByTag:[['string', 'Tag'], ["number", "number of use"]],
                TestCaseCountGroupByTagData:Tag.all.collect{t -> [t.name, TestCase.executeQuery("select count(t.name) as number from TestCase t join t.tags as ts where ts = ?", [t]).first()]},
                TestCaseTotalTimeChart:[['date', 'Date'], ['number', "total"]],
                TestCaseTotalTimeChartData:TestCaseGroupByCreateDate
        ]
        render(template: 'index', model:dashboardModel, layout: 'main')
    }// End of method

}// End of class
