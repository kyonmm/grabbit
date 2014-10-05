import org.kyonmm.grabbit.SecureRole
import org.kyonmm.grabbit.SecureUser
import org.kyonmm.grabbit.SecureUserSecureRole
import org.kyonmm.grabbit.Tag
import org.kyonmm.grabbit.TestCase

class BootStrap {

    def init = { servletContext ->
// ユーザの登録
        def testUser = new SecureUser(
                username: "kyonmm",
                password: "password",
        ).save(flush: true)

// ロールの登録
        def userRole = new SecureRole(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = new SecureRole(authority: 'ROLE_ADMIN').save(failOnError: true)

        SecureUserSecureRole.create(testUser, adminRole).save()

        def useCase = new Tag(name: "ユースケース", description: "エンドユーザーとシステムとのインタラクションを明示的にしたもの").save()
        new Tag(name: "スケーラビリティ", description: "システムのスケーラビリティを明示的にしたもの").save()
        new Tag(name: "スムーズさ", description: "システムのなめらかさを明示的にしたもの").save()
        new Tag(name: "システムレベルのDI", description: "システムの挙動を設定ファイルで変更することを明示的にしたもの").save()
        new Tag(name: "追跡性", description: "システムの過去の挙動に関する立証性を明示的にしたもの").save()
        new Tag(name: "学習曲線", description: "システムを理解し、習得していく流れを明示的にしたもの").save()

        new TestCase(name: "テストケースにはタグがひもづいている", tags: [useCase],
                scenario: "テストケースには登録されているタグが0から任意の個数まで重複なく登録されている。").save()
    }
    def destroy = {
    }
}
