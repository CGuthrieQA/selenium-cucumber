
Feature: Google various search terms
  I want to search for different terms

  #Scenario: Google kitten images
    #Given that I can access google
    #When I search for kittens
    #And I select the images tab
    #Then I should be able to view images of kittnes

  Scenario Outline: Google "<Animal>" images
    Given that I can access "<Domain>"
    When I search for "<Animal>"
    And I select the images tab
    Then I should be able to view images of "<Animal>"

    Examples: 
      | Animal | Domain |
      | kittens | www.google.com |
      | puppies | www.google.com |
      | newts | www.google.com |
      | parrots | www.google.com |
      | rabbits | www.google.com |
      | hamsters | www.google.com |
