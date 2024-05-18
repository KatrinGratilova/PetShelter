package org.katrin.serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.katrin.Pet;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PetSerializer {
    private final ObjectMapper mapper;

    public PetSerializer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public void serialize(File file, Pet pet) throws IOException {
        this.mapper.writeValue(file, pet);
    }

    public void serializeList(File file, List<Pet> pets) throws IOException {
        this.mapper.writeValue(file, pets);
    }

    public Pet deserialize(File file) throws IOException {
        return mapper.readValue(file, Pet.class);
    }

    public List<Pet> deserializeList(File file) throws IOException {
        return mapper.readValue(file, new TypeReference<List<Pet>>(){});
    }
}
