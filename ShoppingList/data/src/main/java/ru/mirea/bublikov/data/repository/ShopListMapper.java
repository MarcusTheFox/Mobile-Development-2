package ru.mirea.bublikov.data.repository;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.bublikov.data.storage.database.ShopItemDbModel;
import ru.mirea.bublikov.domain.models.ShopItem;

public class ShopListMapper {
    public ShopItem mapDbModelToEntity(ShopItemDbModel dbModel) {
        ShopItem entity = new ShopItem(dbModel.name, dbModel.count, dbModel.enabled);
        entity.setId(dbModel.id);
        return entity;
    }

    public ShopItemDbModel mapEntityToDbModel(ShopItem entity) {
        ShopItemDbModel dbModel = new ShopItemDbModel();

        if (entity.getId() != ShopItem.UNDEFINED_ID) {
            dbModel.id = entity.getId();
        }
        dbModel.name = entity.getName();
        dbModel.count = entity.getCount();
        dbModel.enabled = entity.isEnabled();
        return dbModel;
    }

    public List<ShopItem> mapListDbModelToListEntity(List<ShopItemDbModel> list) {
        List<ShopItem> result = new ArrayList<>();
        for (ShopItemDbModel dbModel : list) {
            result.add(mapDbModelToEntity(dbModel));
        }
        return result;
    }
}