import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.*
import jakarta.inject.Inject

@MicronautTest
class MathServiceSpec extends Specification {
    @Inject
    MathService mathService

    @Unroll
    void "should compute #num times 4"()
        when:
        def result = mathService.compute(num)
        then:
        result == expected
        where:
        num | expected
        2   | 8
        3   | 12
    }
}