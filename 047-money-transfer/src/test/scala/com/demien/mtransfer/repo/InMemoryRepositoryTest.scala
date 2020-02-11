package com.demien.mtransfer.repo

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class InMemoryRepositoryTest extends FunSuite {

  case class TestException(msg: String) extends RuntimeException(msg)

  case class TestClass(data: String) {
    def fail(): TestClass = throw TestException("Some exception")
  }

  val repo = new InMemoryRepository[TestClass]

  test("test update") {
    val toSave = TestClass("initial")
    val savedId1 = repo.save(toSave)
    val forUpdate = TestClass("forUpdate")
    repo.update(savedId1, forUpdate)
    val updated = repo.getById(savedId1)
    assert(updated === forUpdate)
  }

  test("testBulkUpdate: success") {
    val savedId1 = repo.save(TestClass("bulk-one"))
    val savedId2 = repo.save(TestClass("bulk-two"))
    repo.bulkUpdate(Seq(
      (savedId1, entity => entity.copy(data = "bulk-one-updated")),
      (savedId2, entity => entity.copy(data = "bulk-two-updated"))
    ))
    assert(repo.getById(savedId1) === TestClass("bulk-one-updated"))
    assert(repo.getById(savedId2) === TestClass("bulk-two-updated"))
  }

  test("testBulkUpdate: fail") {
    val savedId1 = repo.save(TestClass("bulk-one"))
    val savedId2 = repo.save(TestClass("bulk-two"))

    intercept[OperationExecutionException] {
      repo.bulkUpdate(Seq(
        (savedId1, entity => entity.copy(data = "bulk-one-updated")),
        (savedId2, entity => entity.fail())
      ))
    }

    assert(repo.getById(savedId1) === TestClass("bulk-one"))
    assert(repo.getById(savedId2) === TestClass("bulk-two"))
  }

  test("testGetById") {
    val toSave = TestClass("one")
    val savedId1 = repo.save(toSave)
    val saved = repo.getById(savedId1)
    assert(saved === toSave)
  }

  test("testSave") {
    val savedId1 = repo.save(TestClass("one"))
    val savedId2 = repo.save(TestClass("two"))
    assert(savedId1 + 1 === savedId2)
  }

  test("testFind") {
    val savedId1 = repo.save(TestClass("find-one"))
    val savedId2 = repo.save(TestClass("find-two"))

    val found1 = repo.find(e => e.data.contains("find-one"))
    assert(found1.length === 1)
    assert(found1.head._1 === savedId1)
    assert(found1.head._2 === TestClass("find-one"))

    val found2 = repo.find(e => e.data.contains("find"))
    assert(found2.length === 2)


  }

}
