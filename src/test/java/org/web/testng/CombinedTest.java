package org.web.testng;

public class CombinedTest extends AbstractNGTest {

//    @Test
//    public void getAndStorePerson() {
//        getRandomPerson(2).forEach(p -> DbUtil.storeInDB(p.getInsertSQL()));
//        PersonDto personDto = PersonDto.getRandomPersonFromDB();
//
//        if (personDto == null) {
//            Assert.fail("Could not extract random person from DB");
//        }
//        String personName = personDto.getName().getFirst() + " " + personDto.getName().getLast();
//        googlePage.setSearchText(personName);
//        googlePage.performSearch();
//        Assert.assertTrue(
//                googlePage.getSearchHeaders()
//                        .stream()
//                        .anyMatch(
//                                we -> we.getText().toUpperCase().contains(personName.toUpperCase())
//                        ), "No person with this name found! " + personName
//        );
//    }
//
//    private List<PersonDto> getRandomPerson(int amount) {
//        RestAssured.baseURI = "https://randomuser.me/";
//        return RestAssured.given()
//                .basePath("/api")
//                .queryParam("inc", "gender,name,nat")
//                .queryParam("noinfo")
//                .queryParam("results", amount)
//                .get()
//                .then()
//                .contentType(ContentType.JSON)
//                .statusCode(200)
//                .extract()
//                .as(ResultsDto.class)
//                .getResults();
//    }
}
