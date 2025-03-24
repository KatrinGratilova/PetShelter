package org.katrin.model;

public class Pet {
    private int id;
    private String name;
    private String type;
    private String breed;
    private int age;
    private Gender gender;

    public Pet(int id, String name, String type, String breed, int age, Gender gender) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
    }

    public Pet() {
    }

    public static PetBuilder builder() {
        return new PetBuilder();
    }

    public String toString() {
        return "Pet №" + id + ":" + "\n - name - " + this.getName() + "\n - type - " + this.getType() + "\n - breed - " + this.getBreed() + "\n - age - " +
                this.getAge() + "\n - gender - " + this.getGender().getMessage();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getBreed() {
        return this.breed;
    }

    public int getAge() {
        return this.age;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Pet)) return false;
        final Pet other = (Pet) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$breed = this.getBreed();
        final Object other$breed = other.getBreed();
        if (this$breed == null ? other$breed != null : !this$breed.equals(other$breed)) return false;
        if (this.getAge() != other.getAge()) return false;
        final Object this$gender = this.getGender();
        final Object other$gender = other.getGender();
        if (this$gender == null ? other$gender != null : !this$gender.equals(other$gender)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Pet;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $breed = this.getBreed();
        result = result * PRIME + ($breed == null ? 43 : $breed.hashCode());
        result = result * PRIME + this.getAge();
        final Object $gender = this.getGender();
        result = result * PRIME + ($gender == null ? 43 : $gender.hashCode());
        return result;
    }

    public static class PetBuilder {
        private int id;
        private String name;
        private String type;
        private String breed;
        private int age;
        private Gender gender;

        PetBuilder() {
        }

        public PetBuilder id(int id) {
            this.id = id;
            return this;
        }

        public PetBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PetBuilder type(String type) {
            this.type = type;
            return this;
        }

        public PetBuilder breed(String breed) {
            this.breed = breed;
            return this;
        }

        public PetBuilder age(int age) {
            this.age = age;
            return this;
        }

        public PetBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Pet build() {
            return new Pet(this.id, this.name, this.type, this.breed, this.age, this.gender);
        }

        public String toString() {
            return "Pet.PetBuilder(id=" + this.id + ", name=" + this.name + ", type=" + this.type + ", breed=" + this.breed + ", age=" + this.age + ", gender=" + this.gender + ")";
        }
    }
}
